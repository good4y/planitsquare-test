package com.planitsquaretest.holiday.repository;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.holiday.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    @Modifying
    @Query("delete from Holiday h where h.country = :country and h.date between :start and :end")
    void deleteAllByDateBetweenAndCountry(LocalDate start, LocalDate end, Country country);
}
