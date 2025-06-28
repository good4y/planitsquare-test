package com.planitsquaretest.common.utils;

import com.planitsquaretest.common.dto.CsvResultResponse;
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
            CsvResultResponse<GoogleCalendarCsvDto> googleCalendarCsvDtos = CsvUtil.read(reader, GoogleCalendarCsvDto.class);

            // then
            List<GoogleCalendarCsvDto> data = googleCalendarCsvDtos.data();
            int failedCount = googleCalendarCsvDtos.failedCount();

            assertThat(failedCount).isEqualTo(0);
            assertThat(data.size()).isEqualTo(1);
            assertThat(data.getFirst().getCalendarId()).isEqualTo(calendarId);
            assertThat(data.getFirst().getCountryCode()).isEqualTo(koreaIsoCode);
            assertThat(data.getFirst().getCountry()).isEqualTo(countryName);
        }

        @Test
        void csv_데이터_중_빈_컬럼을_읽고_에러를_기록한다() {
            // given
            String koreaIsoCode = "KR";
            String countryName = "South Korea";

            String csv = String.format("""
                    CountryCode,Country,CalendarId
                    %s,%s,%s
                    """, koreaIsoCode, countryName, null);

            Reader reader = new StringReader(csv);

            // when
            CsvResultResponse<GoogleCalendarCsvDto> googleCalendarCsvDtos = CsvUtil.read(reader, GoogleCalendarCsvDto.class);

            // then
            List<GoogleCalendarCsvDto> data = googleCalendarCsvDtos.data();
            int failedCount = googleCalendarCsvDtos.failedCount();

            assertThat(failedCount).isEqualTo(1);
            assertThat(data.size()).isEqualTo(0);
        }
    }
}