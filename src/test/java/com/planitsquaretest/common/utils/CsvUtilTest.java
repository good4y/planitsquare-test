package com.planitsquaretest.common.utils;

import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvUtilTest {

    @Nested
    class read {

        @Test
        void csv를_읽고_DTO를_반환한다() {
            // given
            String koreaIsoCode = "KR";
            String countryName = "South Korea";
            String calendarId = "kr@calendar.google.com";

            String csv = String.format("""
                    CountryCode,Country,CalendarId
                    %s,%s,%s
                    """, koreaIsoCode, countryName, calendarId);

            Reader reader = new StringReader(csv);

            // when
            List<GoogleCalendarCsvDto> googleCalendarCsvDtos = CsvUtil.read(reader, GoogleCalendarCsvDto.class);

            // then
            GoogleCalendarCsvDto result = googleCalendarCsvDtos.getFirst();

            assertThat(result).isNotNull();
            assertThat(result.getCountryCode()).isEqualTo(koreaIsoCode);
            assertThat(result.getCountry()).isEqualTo(countryName);
            assertThat(result.getCalendarId()).isEqualTo(calendarId);
        }

    }
}