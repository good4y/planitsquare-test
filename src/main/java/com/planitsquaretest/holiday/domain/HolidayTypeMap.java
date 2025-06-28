package com.planitsquaretest.holiday.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HolidayTypeMap {
    @EmbeddedId
    private HolidayTypeMapId id;

    @Setter
    @MapsId("holidayId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Holiday holiday;

    @Setter
    @MapsId("holidayTypeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private HolidayTypeEntity holidayType;

    @Builder
    private HolidayTypeMap(Holiday holiday, HolidayTypeEntity holidayType) {
        this.holiday = holiday;
        this.holidayType = holidayType;
        this.id = HolidayTypeMapId.builder()
                .holidayId(holiday.getId())
                .holidayTypeId(holidayType.getId())
                .build();
    }
}
