package com.planitsquaretest.country.facade;

import com.planitsquaretest.country.dto.NagerAvailableCountryDto;
import com.planitsquaretest.country.service.CountryApiService;
import com.planitsquaretest.country.service.CountryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryQueryFacade {

    private final CountryApiService countryApiService;
    private final CountryQueryService countryQueryService;

    public List<NagerAvailableCountryDto> getAvailableCountryFromApi() {
        return countryApiService.getAvailableCountryFromNager();
    }

    public List<String> getAllCountryCode() {
        return countryQueryService.findAllCountryCodes();
    }
}
