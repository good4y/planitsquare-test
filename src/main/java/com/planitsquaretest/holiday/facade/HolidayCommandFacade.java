package com.planitsquaretest.holiday.facade;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.country.dto.CountryDto;
import com.planitsquaretest.country.service.CountryQueryService;
import com.planitsquaretest.holiday.domain.*;
import com.planitsquaretest.holiday.dto.NagerPublicHolidayDto;
import com.planitsquaretest.holiday.service.HolidayApiService;
import com.planitsquaretest.holiday.service.HolidayCommandService;
import com.planitsquaretest.holiday.service.HolidayQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HolidayCommandFacade {

    private final HolidayApiService holidayApiService;
    private final HolidayCommandService holidayCommandService;
    private final CountryQueryService countryQueryService;
    private final HolidayQueryService holidayQueryService;

    public void saveAllHolidayFromNager(String year, String countryCode) {
        CountryDto countryDto = countryQueryService.findByCountryCode(countryCode);
        Country country = countryDto.country();

        List<NagerPublicHolidayDto> holidaysFromNager = holidayApiService.getHolidaysFromNager(year, countryCode);
        List<Holiday> holidays = new ArrayList<>();

        for (NagerPublicHolidayDto dto : holidaysFromNager) {
            Holiday holiday = Holiday.builder()
                    .name(dto.name())
                    .localName(dto.localName())
                    .date(dto.date())
                    .isGlobal(dto.global())
                    .build();

            // HolidayType
            List<HolidayTypeMap> typeEntities = Optional.ofNullable(dto.types())
                    .map(types -> types.stream()
                            .map(type -> {
                                HolidayTypeEntity typeEntity = holidayQueryService.findHolidayTypeEntityByType(type)
                                        .holidayType();

                                HolidayTypeMap map = HolidayTypeMap.builder()
                                        .holidayType(typeEntity)
                                        .holiday(holiday)
                                        .build();

                                holiday.addHolidayTypeMap(map);

                                return map;
                            })
                            .collect(Collectors.toList()))
                    .orElseGet(Collections::emptyList);

            // counties
            List<HolidayDetail> holidayDetails = new ArrayList<>(Optional.ofNullable(dto.counties())
                    .map(counties -> counties.stream()
                            .map(county -> {
                                HolidayDetail detail = HolidayDetail.builder()
                                        .type(HolidayDetailType.COUNTY)
                                        .content(county)
                                        .sourceType(SourceType.NAGER)
                                        .build();
                                holiday.addDetail(detail);
                                return detail;
                            })
                            .collect(Collectors.toList()))
                    .orElseGet(Collections::emptyList));

            // launchYear
            if (dto.launchYear() != null) {
                HolidayDetail launchYear = HolidayDetail.builder()
                        .sourceType(SourceType.NAGER)
                        .content(dto.launchYear())
                        .type(HolidayDetailType.LAUNCH_YEAR)
                        .build();

                holidayDetails.add(launchYear);
            }

            holiday.addListHolidayTypeMap(typeEntities);
            holiday.addListDetails(holidayDetails);

            country.addHoliday(holiday);
            holidays.add(holiday);
        }

        holidayCommandService.saveAllHoliday(holidays);
    }
}
