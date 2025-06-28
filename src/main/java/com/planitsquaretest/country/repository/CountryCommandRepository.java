package com.planitsquaretest.country.repository;

import com.planitsquaretest.country.domain.Country;
import com.planitsquaretest.public_.tables.records.CountryRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.planitsquaretest.public_.tables.Country.COUNTRY;

@Repository
@Transactional
@RequiredArgsConstructor
public class CountryCommandRepository {

    private final DSLContext dsl;

    public void batchInsertCountries(List<Country> countries) {
        List<CountryRecord> countryRecord = toCountryRecords(countries);
        dsl.batchInsert(countryRecord)
                .execute();
    }

    private List<CountryRecord> toCountryRecords(List<Country> countries) {
        LocalDateTime now = LocalDateTime.now();

        return countries.stream()
                .map(country -> {
                    CountryRecord record = dsl.newRecord(COUNTRY);
                    record.setName(country.getName());
                    record.setCode(country.getCode());
                    record.setCreatedAt(now);
                    record.setModifiedAt(now);
                    return record;
                })
                .collect(Collectors.toList());
    }
}