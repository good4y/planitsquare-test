package com.planitsquaretest.holiday.facade;

import com.planitsquaretest.holiday.dto.NagerPublicHolidayDto;
import com.planitsquaretest.holiday.service.HolidayApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayQueryFacade {

    private final HolidayApiService holidayApiService;

    public List<NagerPublicHolidayDto> getHolidaysFromApi(String year, String countryCode) {
        return holidayApiService.getHolidaysFromNager(year, countryCode);
    }
}
