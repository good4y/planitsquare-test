package com.planitsquaretest.common.service;

import com.planitsquaretest.common.dto.CsvResultResponse;
import com.planitsquaretest.common.exception.InternalServerErrorException;
import com.planitsquaretest.common.utils.CsvUtil;
import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvServiceTest {

    @InjectMocks
    private CsvService csvService;

    @Nested
    class readCsv {

        @Test
        void CSV_파일이_존재하면_정상적으로_값을_반환한다() {
            // given
            String path = "/test_csv_valid.csv";

            GoogleCalendarCsvDto mockDto = mock(GoogleCalendarCsvDto.class);
            when(mockDto.getCountryCode()).thenReturn("KR");
            when(mockDto.getCountry()).thenReturn("Korea");
            when(mockDto.getCalendarId()).thenReturn("id");

            CsvResultResponse<GoogleCalendarCsvDto> mockResult =
                    new CsvResultResponse<>(List.of(mockDto), 0);

            try (MockedStatic<CsvUtil> mocked = mockStatic(CsvUtil.class)) {
                mocked.when(() -> CsvUtil.read(any(), eq(GoogleCalendarCsvDto.class)))
                        .thenReturn(mockResult);

                // when
                CsvResultResponse<GoogleCalendarCsvDto> result =
                        csvService.readCsv(path, GoogleCalendarCsvDto.class);

                // then
                assertThat(result.data()).hasSize(1);
                assertThat(result.failedCount()).isEqualTo(0);

                GoogleCalendarCsvDto dto = result.data().getFirst();
                assertThat(dto.getCountryCode()).isEqualTo("KR");
                assertThat(dto.getCountry()).isEqualTo("Korea");
                assertThat(dto.getCalendarId()).isEqualTo("id");
            }
        }

        @Test
        void 파일이_존재하지_않으면_예외를_던진다() {
            // given
            String wrongPath = "/not_exists.csv";

            // when & then
            assertThatThrownBy(() ->
                    csvService.readCsv(wrongPath, GoogleCalendarCsvDto.class)
            ).isInstanceOf(InternalServerErrorException.class)
                    .hasMessageContaining("CSV 파일을 찾을 수 없습니다");
        }
    }
}