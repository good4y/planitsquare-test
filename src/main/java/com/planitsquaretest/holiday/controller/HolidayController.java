package com.planitsquaretest.holiday.controller;

import com.planitsquaretest.api.HolidayApi;
import com.planitsquaretest.holiday.dto.HolidaySearchConditionDto;
import com.planitsquaretest.holiday.dto.HolidaySearchResponseDto;
import com.planitsquaretest.holiday.facade.HolidayCommandFacade;
import com.planitsquaretest.holiday.facade.HolidayQueryFacade;
import com.planitsquaretest.model.HolidayViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HolidayController implements HolidayApi {

    private final HolidayQueryFacade holidayQueryFacade;
    private final HolidayCommandFacade holidayCommandFacade;

    @Override
    public void holidayDelete(Integer year, String countryCode) {
        holidayCommandFacade.deleteHoliday(year, countryCode);
    }

    @Override
    public void holidayRefresh(Integer year, String countryCode) {
        holidayCommandFacade.refreshHolidayFromNager(year, countryCode);
    }

    @Override
    public List<HolidayViewResponse> holidaySearch(Integer year, String countryCode, Integer offset, Integer limit) {
        HolidaySearchConditionDto condition = new HolidaySearchConditionDto(year, countryCode, offset, limit);

        List<HolidaySearchResponseDto> holidaysByCondition = holidayQueryFacade.getHolidaysByCondition(condition);

        return holidaysByCondition.stream()
                .map(HolidaySearchResponseDto::toHolidayViewResponse)
                .toList();
    }
}
