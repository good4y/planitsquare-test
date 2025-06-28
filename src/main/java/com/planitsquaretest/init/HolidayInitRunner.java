package com.planitsquaretest.init;

import com.planitsquaretest.country.facade.CountryQueryFacade;
import com.planitsquaretest.holiday.facade.HolidayCommandFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Order(300)
@Component
@RequiredArgsConstructor
public class HolidayInitRunner implements ApplicationRunner {

    private final HolidayCommandFacade holidayCommandFacade;
    private final CountryQueryFacade countryQueryFacade;

    @Override
    public void run(ApplicationArguments args) {
        log.info("HolidayInitRunner started");
        int currentYear = LocalDate.now().getYear();
        List<String> allCountryCode = countryQueryFacade.getAllCountryCode();

        for (String countryCode : allCountryCode) {
            for (int i = 0; i < 5; i++) {
                String year = String.valueOf(currentYear - i);
                holidayCommandFacade.saveAllHolidayFromNager(year, countryCode);
            }
        }
    }
}
