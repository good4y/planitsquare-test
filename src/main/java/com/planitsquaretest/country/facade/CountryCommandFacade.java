package com.planitsquaretest.country.facade;

import com.planitsquaretest.common.dto.CsvResultResponse;
import com.planitsquaretest.common.service.CsvService;
import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import com.planitsquaretest.country.dto.NagerAvailableCountryDto;
import com.planitsquaretest.country.service.CountryApiService;
import com.planitsquaretest.country.service.CountryService;
import com.planitsquaretest.model.CountryCsvRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryCommandFacade {

    private final CountryService countryService;
    private final CsvService csvService;
    private final CountryApiService countryApiService;

    public CountryCsvRegisterResponse saveAllCountryFromCsv() {
        String filePath = "/google_calendar_id.csv";

        CsvResultResponse<GoogleCalendarCsvDto> csvResult =
                csvService.readCsv(filePath, GoogleCalendarCsvDto.class);

        List<GoogleCalendarCsvDto> data = csvResult.data();
        countryService.saveAllFromCsv(data);

        return new CountryCsvRegisterResponse()
                .total(data.size())
                .failed(csvResult.failedCount());
    }

    public void saveAllCountryFromNager() {
        List<NagerAvailableCountryDto> apiResponse = countryApiService.getAvailableCountryFromNager();

        List<Country> countries = apiResponse.stream()
                .map(res -> Country
                        .builder()
                        .code(res.countryCode())
                        .name(res.name())
                        .build()
                ).toList();
        countryService.saveAllCountries(countries);
    }
}
