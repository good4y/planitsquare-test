package com.planitsquaretest.country.service;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.country.domain.CountryDetail;
import com.planitsquaretest.country.domain.CountryDetailType;
import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import com.planitsquaretest.country.repository.CountryCommandRepository;
import com.planitsquaretest.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryCommandRepository countryCommandRepository;

    public void saveAllFromCsv(List<GoogleCalendarCsvDto> dtos) {
        CountryDetailType type = CountryDetailType.GOOGLE_CALENDAR_ID;
        List<Country> countries = new ArrayList<>();

        dtos.forEach(dto -> {
            Country country = Country.builder()
                    .name(dto.getCountry())
                    .code(dto.getCountryCode())
                    .build();

            CountryDetail countryDetail = CountryDetail.builder()
                    .type(type)
                    .description(type.getDescription())
                    .content(dto.getCalendarId())
                    .build();

            country.addCountryDetail(countryDetail);
            countries.add(country);
        });

        countryRepository.saveAll(countries);
    }

    public void saveAllCountries(List<Country> countries) {
        countryCommandRepository.batchInsertCountries(countries);
    }
}
