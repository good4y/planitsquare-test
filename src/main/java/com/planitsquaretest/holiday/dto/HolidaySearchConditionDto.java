package com.planitsquaretest.holiday.dto;

public record HolidaySearchConditionDto(
        Integer year,
        String countryCode,
        Integer offset,
        Integer limit
) {
}