package com.planitsquaretest.holiday.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "holiday_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HolidayTypeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    private String description;

    @OneToMany(mappedBy = "holidayType")
    private List<HolidayTypeMap> typeMaps = new ArrayList<>();

    @Builder
    private HolidayTypeEntity(String description, HolidayType type) {
        this.description = description;
        this.type = type;
    }
}