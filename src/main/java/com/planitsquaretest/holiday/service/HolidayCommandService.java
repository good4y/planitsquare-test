package com.planitsquaretest.holiday.service;

import com.planitsquaretest.holiday.domain.Holiday;
import com.planitsquaretest.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HolidayCommandService {

    private final HolidayRepository holidayRepository;

    public void saveAllHoliday(List<Holiday> holidays){
        holidayRepository.saveAll(holidays);
    }
}
