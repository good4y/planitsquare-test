package com.planitsquaretest.country.service;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.country.domain.CountryDetail;
import com.planitsquaretest.country.domain.CountryDetailType;
import com.planitsquaretest.country.dto.GoogleCalendarCsvDto;
import com.planitsquaretest.country.repository.CountryCommandRepository;
import com.planitsquaretest.country.repository.CountryRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryCommandRepository countryCommandRepository;

    @Nested
    class saveAllFromCsv {

        @Test
        void csv_파일을_이용해_국가정보와_세부정보를_저장한다() {
            // given
            String koreaCountryCode = "KR";
            String koreaCountryName = "Korea";
            String calendarId = "id";
            GoogleCalendarCsvDto mockDto = mock(GoogleCalendarCsvDto.class);

            when(mockDto.getCountry()).thenReturn(koreaCountryName);
            when(mockDto.getCountryCode()).thenReturn(koreaCountryCode);
            when(mockDto.getCalendarId()).thenReturn(calendarId);

            List<GoogleCalendarCsvDto> dtos = List.of(mockDto);

            // when
            countryService.saveAllFromCsv(dtos);

            ArgumentCaptor<List<Country>> captor = ArgumentCaptor.forClass(List.class);
            verify(countryRepository).saveAll(captor.capture());

            List<Country> captured = captor.getValue();
            assertThat(captured).hasSize(1);

            Country country = captured.getFirst();
            CountryDetail countryDetail = country.getCountryDetails().getFirst();

            assertThat(country.getCode()).isEqualTo(koreaCountryCode);
            assertThat(country.getName()).isEqualTo(koreaCountryName);
            assertThat(country.getCountryDetails()).hasSize(1);
            assertThat(countryDetail.getContent()).isEqualTo(calendarId);
            assertThat(countryDetail.getType()).isEqualTo(CountryDetailType.GOOGLE_CALENDAR_ID);
        }
    }

    @Nested
    class saveAllCountries {

        @Test
        void 국가_정보를_저장한다() {
            // given
            List<Country> countries = List.of(
                    Country.builder().name("korea").code("KR").build(),
                    Country.builder().name("United States").code("US").build()
            );

            // when
            countryService.saveAllCountries(countries);

            // then
            verify(countryCommandRepository).batchInsertCountries(countries);
        }

    }
}