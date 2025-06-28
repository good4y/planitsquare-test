package com.planitsquaretest.init;

import com.planitsquaretest.holiday.domain.HolidayType;
import com.planitsquaretest.holiday.domain.HolidayTypeEntity;
import com.planitsquaretest.holiday.repository.HolidayTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(100)
@Component
@RequiredArgsConstructor
public class HolidayTypeInitRunner implements ApplicationRunner {

    private final HolidayTypeEntityRepository holidayTypeEntityRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("HolidayTypeInitRunner started");
        HolidayType[] types = HolidayType.values();

        for (HolidayType type : types) {
            HolidayTypeEntity entity = HolidayTypeEntity.builder()
                    .type(type)
                    .description(type.getDescription())
                    .build();

            holidayTypeEntityRepository.save(entity);
        }

        log.info("HolidayTypeInitRunner finished");
    }
}
