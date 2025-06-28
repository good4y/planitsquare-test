package com.planitsquaretest.holiday.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum HolidayType {
    DEFAULT(""),
    PUBLIC(""),
    BANK("Bank holiday, banks and offices are closed"),
    SCHOOL("School holiday, schools are closed"),
    AUTHORITIES("Authorities are closed"),
    OPTIONAL("Majority of people take a day off"),
    OBSERVANCE("Optional festivity, no paid day off");

    final String description;

    HolidayType(String description) {
        this.description = description;
    }

    @JsonCreator
    public static HolidayType from(String value) {
        if (value == null) return null;
        return HolidayType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
