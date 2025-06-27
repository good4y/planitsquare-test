package com.planitsquaretest.country.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class GoogleCalendarCsvDto {
    @CsvBindByName(column = "CountryCode", required = true)
    String countryCode;
    @CsvBindByName(column = "Country", required = true)
    String country;
    @CsvBindByName(column = "CalendarId", required = true)
    String calendarId;
}
