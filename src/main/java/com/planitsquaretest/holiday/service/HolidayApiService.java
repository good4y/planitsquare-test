package com.planitsquaretest.holiday.service;

import com.planitsquaretest.common.exception.InternalServerErrorException;
import com.planitsquaretest.holiday.dto.NagerPublicHolidayDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayApiService {

    private final RestClient nagerRestClient;

    public List<NagerPublicHolidayDto> getHolidaysFromNager(String year, String countryCode){
        return nagerRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("PublicHolidays")
                        .pathSegment(year)
                        .pathSegment(countryCode)
                        .build())
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        this::handleClientError)
                .body(new ParameterizedTypeReference<>() {});
    }

    private void handleClientError(HttpRequest request, ClientHttpResponse response) {
        try {
            String messages = new String(response.getBody().readAllBytes());

            String startKeyword = "\"status_message\":\"";
            String endKeyword = "\"";
            String message = StringUtils.substringBetween(messages, startKeyword, endKeyword);

            throw InternalServerErrorException.serverError(message);
        } catch (IOException e) {
            throw InternalServerErrorException.serverError("외부 응답 읽기 실패", e);
        }
    }
}
