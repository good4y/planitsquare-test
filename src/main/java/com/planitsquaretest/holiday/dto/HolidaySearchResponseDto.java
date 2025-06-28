package com.planitsquaretest.holiday.dto;

import com.planitsquaretest.model.HolidayViewResponse;
import com.planitsquaretest.model.HolidayViewResponseDetailsInner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record HolidaySearchResponseDto(
        String countryName,
        LocalDate holidayDate,
        String holidayName,
        String holidayLocalName,
        String countryCode,
        boolean isGlobal,
        List<HolidayDetailSearchResponseDto> details
) {
    public record HolidayDetailSearchResponseDto(
            String detailType,
            String value
    ) {
    }

    public HolidayViewResponse toHolidayViewResponse() {
        List<HolidayViewResponseDetailsInner> mappedDetails = details.stream()
                .map(d -> {
                    HolidayViewResponseDetailsInner inner = new HolidayViewResponseDetailsInner();
                    inner.setDetailType(d.detailType());
                    inner.setValue(d.value());
                    return inner;
                })
                .collect(Collectors.toList());

        HolidayViewResponse response = new HolidayViewResponse();
        response.setCountryName(countryName);
        response.setHolidayDate(holidayDate);
        response.setHolidayName(holidayName);
        response.setHolidayLocalName(holidayLocalName);
        response.setCountryCode(countryCode);
        response.setIsGlobal(isGlobal);
        response.setDetails(mappedDetails);

        return response;
    }
}
