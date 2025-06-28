package com.planitsquaretest.holiday.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HolidayTypeMapId implements Serializable {
    private Long holidayId;
    private Long holidayTypeId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HolidayTypeMapId that = (HolidayTypeMapId) o;
        return Objects.equals(getHolidayId(), that.getHolidayId()) && Objects.equals(getHolidayTypeId(), that.getHolidayTypeId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getHolidayId());
        result = 31 * result + Objects.hashCode(getHolidayTypeId());
        return result;
    }

    @Builder
    private HolidayTypeMapId(Long holidayId, Long holidayTypeId) {
        this.holidayId = holidayId;
        this.holidayTypeId = holidayTypeId;
    }
}