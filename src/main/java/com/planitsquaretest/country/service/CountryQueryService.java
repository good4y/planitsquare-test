package com.planitsquaretest.country.service;

import com.planitsquaretest.common.exception.NotFoundException;
import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.country.dto.CountryDto;
import com.planitsquaretest.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryQueryService {

    private final CountryRepository countryRepository;

    public CountryDto findByCountryCode(String code) {
        Country country = countryRepository.findByCode(code).orElseThrow(
                () -> NotFoundException.notFound("Country with code " + code + " not found"));

        return new CountryDto(country);
    }

    public List<String> findAllCountryCodes() {
        return countryRepository.findAllCountryCodes();
    }
}
