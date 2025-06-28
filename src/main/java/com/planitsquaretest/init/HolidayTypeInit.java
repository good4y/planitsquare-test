package com.planitsquaretest.init;

import com.planitsquaretest.holiday.domain.HolidayType;
import com.planitsquaretest.holiday.domain.HolidayTypeEntity;
import com.planitsquaretest.holiday.repository.HolidayTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayTypeInit implements ApplicationRunner {

    private final HolidayTypeEntityRepository holidayTypeEntityRepository;

    @Override
    public void run(ApplicationArguments args) {
        HolidayType[] types = HolidayType.values();

        for (HolidayType type : types) {
            HolidayTypeEntity entity = HolidayTypeEntity.builder()
                    .type(type)
                    .description(type.getDescription())
                    .build();

            holidayTypeEntityRepository.save(entity);
        }
    }
}
