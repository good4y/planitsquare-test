package com.planitsquaretest.holiday.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import com.planitsquaretest.country.domain.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Holiday extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    private String name;

    private LocalDate date;

    @Builder
    private Holiday(Country country, LocalDate date, Long id, String name) {
        this.country = country;
        this.date = date;
        this.id = id;
        this.name = name;
    }
}
