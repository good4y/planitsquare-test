package com.planitsquaretest.holiday.service;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.holiday.domain.Holiday;
import com.planitsquaretest.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HolidayCommandService {

    private final HolidayRepository holidayRepository;

    public void saveAllHoliday(List<Holiday> holidays){
        holidayRepository.saveAll(holidays);
    }

    public void deleteHolidayYearCountry(int year, Country country) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        holidayRepository.deleteAllByDateBetweenAndCountry(start, end, country);
    }
}
