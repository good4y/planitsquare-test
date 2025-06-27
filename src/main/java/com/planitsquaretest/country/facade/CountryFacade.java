package com.planitsquaretest.country.facade;

import com.planitsquaretest.common.dto.CsvResultResponse;
import com.planitsquaretest.common.service.CsvService;
import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import com.planitsquaretest.country.service.CountryService;
import com.planitsquaretest.model.CountryCsvRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryFacade {

    private final CountryService countryService;
    private final CsvService csvService;

    @Transactional
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
}
