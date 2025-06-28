package com.planitsquaretest.holiday.facade;

import com.planitsquaretest.country.dto.CountryDto;
import com.planitsquaretest.country.service.CountryQueryService;
import com.planitsquaretest.holiday.dto.HolidaySearchConditionDto;
import com.planitsquaretest.holiday.dto.HolidaySearchResponseDto;
import com.planitsquaretest.holiday.dto.NagerPublicHolidayDto;
import com.planitsquaretest.holiday.service.HolidayApiService;
import com.planitsquaretest.holiday.service.HolidayQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayQueryFacade {

    private final HolidayApiService holidayApiService;
    private final HolidayQueryService holidayQueryService;
    private final CountryQueryService countryQueryService;

    public List<NagerPublicHolidayDto> getHolidaysFromApi(String year, String countryCode) {
        return holidayApiService.getHolidaysFromNager(year, countryCode);
    }

    public List<HolidaySearchResponseDto> getHolidaysByCondition(HolidaySearchConditionDto condition) {
        Long countryId = null;

        if(condition.countryCode() != null) {
            CountryDto countryDto = countryQueryService.findByCountryCode(condition.countryCode());
            countryId = countryDto.country().getId();
        }

        return holidayQueryService.findHolidaysByCondition(condition, countryId);
    }
}
