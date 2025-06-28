package com.planitsquaretest.holiday.repository;

import com.planitsquaretest.holiday.domain.HolidayType;
import com.planitsquaretest.holiday.domain.HolidayTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolidayTypeEntityRepository extends JpaRepository<HolidayTypeEntity, Long> {

    Optional<HolidayTypeEntity> findByType(HolidayType type);
}
