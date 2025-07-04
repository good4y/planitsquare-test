package com.planitsquaretest.country.controller;

import com.planitsquaretest.api.CountryApi;
import com.planitsquaretest.country.facade.CountryCommandFacade;
import com.planitsquaretest.model.CountryCsvRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryController implements CountryApi {

    private final CountryCommandFacade countryCommandFacade;

    @Override
    public CountryCsvRegisterResponse countryCsvRegister() {
        return countryCommandFacade.saveAllCountryFromCsv();
    }
}
