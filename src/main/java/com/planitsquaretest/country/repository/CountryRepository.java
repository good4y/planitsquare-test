package com.planitsquaretest.country.repository;

import com.planitsquaretest.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
