package com.planitsquaretest.holiday.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HolidayDetailType {
    COUNTY, LAUNCH_YEAR;

    @JsonCreator
    public static HolidayDetailType from(String value) {
        if (value == null) return null;
        return HolidayDetailType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
