package com.planitsquaretest.holiday.domain;

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
}
