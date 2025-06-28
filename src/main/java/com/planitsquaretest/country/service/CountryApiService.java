package com.planitsquaretest.country.service;

import com.planitsquaretest.common.exception.InternalServerErrorException;
import com.planitsquaretest.country.dto.NagerAvailableCountryDto;
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
public class CountryApiService {

    private final RestClient nagerRestClient;

    public List<NagerAvailableCountryDto> getAvailableCountryFromNager(){
        return nagerRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("AvailableCountries")
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
