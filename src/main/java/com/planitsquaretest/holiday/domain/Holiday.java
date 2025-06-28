package com.planitsquaretest.holiday.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import com.planitsquaretest.country.domain.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private boolean isGlobal;

    @OneToMany(mappedBy = "holiday", cascade = CascadeType.PERSIST)
    List<HolidayDetail> details = new ArrayList<>();

    @OneToMany(mappedBy = "holiday",  cascade = CascadeType.PERSIST)
    List<HolidayTypeMap> holidayTypes = new ArrayList<>();

    @Builder
    private Holiday(Country country, LocalDate date, Long id, String name, boolean isGlobal) {
        this.country = country;
        this.date = date;
        this.id = id;
        this.name = name;
        this.isGlobal = isGlobal;
    }

    public void addDetail(HolidayDetail detail) {
        this.details.add(detail);
        detail.setHoliday(this);
    }
}
