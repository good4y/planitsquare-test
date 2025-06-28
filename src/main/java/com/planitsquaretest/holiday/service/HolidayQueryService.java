package com.planitsquaretest.holiday.service;

import com.planitsquaretest.common.exception.NotFoundException;
import com.planitsquaretest.holiday.domain.HolidayType;
import com.planitsquaretest.holiday.domain.HolidayTypeEntity;
import com.planitsquaretest.holiday.dto.HolidayTypeEntityDto;
import com.planitsquaretest.holiday.repository.HolidayTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HolidayQueryService {

    private final HolidayTypeEntityRepository holidayTypeEntityRepository;

    public HolidayTypeEntityDto findHolidayTypeEntityByType(HolidayType holidayType) {
        HolidayTypeEntity type = holidayTypeEntityRepository.findByType(holidayType)
                .orElseThrow(
                        () -> NotFoundException.notFound("HolidayType with type " + holidayType + " not found"));

        return new HolidayTypeEntityDto(type);
    }
}
