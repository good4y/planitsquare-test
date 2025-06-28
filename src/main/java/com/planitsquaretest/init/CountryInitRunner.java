package com.planitsquaretest.init;

import com.planitsquaretest.country.facade.CountryCommandFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(200)
@Component
@RequiredArgsConstructor
public class CountryInitRunner implements ApplicationRunner {

    private final CountryCommandFacade countryCommandFacade;

    @Override
    public void run(ApplicationArguments args) {
        log.info("CountryInitRunner started");
        countryCommandFacade.saveAllCountryFromNager();

        log.info("CountryInitRunner finished");

    }
}
