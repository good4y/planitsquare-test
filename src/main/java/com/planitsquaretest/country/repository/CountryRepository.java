package com.planitsquaretest.country.repository;

import com.planitsquaretest.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByCode(String code);
    @Query("SELECT c.code FROM Country c")
    List<String> findAllCountryCodes();
}
