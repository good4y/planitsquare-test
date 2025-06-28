package com.planitsquaretest.country.domain;

import lombok.Getter;

@Getter
public enum CountryDetailType {
    GOOGLE_CALENDAR_ID("google holiday calendar id");

    private final String description;

    CountryDetailType(String s) {
        this.description = s;
    }
}
