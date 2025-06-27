package com.planitsquaretest.country.controller;

import com.planitsquaretest.api.CountryApi;
import com.planitsquaretest.country.facade.CountryFacade;
import com.planitsquaretest.model.CountryCsvRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryController implements CountryApi {

    private final CountryFacade countryFacade;

    @Override
    public CountryCsvRegisterResponse countryCsvRegister() {
        return countryFacade.saveAllCountryFromCsv();
    }
}
