package com.planitsquaretest.holiday.repository;

import com.planitsquaretest.holiday.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
