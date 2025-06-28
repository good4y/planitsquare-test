package com.planitsquaretest.holiday.dto;

import com.planitsquaretest.holiday.domain.HolidayType;

import java.time.LocalDate;
import java.util.List;

public record NagerPublicHolidayDto(
        LocalDate date,
        String localName,
        String name,
        String countryCode,
        boolean global,
        List<String> counties,
        Integer launchYear,
        List<HolidayType> types
) {
}
