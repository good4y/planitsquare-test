package com.planitsquaretest.holiday.repository;

import com.planitsquaretest.holiday.dto.HolidaySearchConditionDto;
import com.planitsquaretest.holiday.dto.HolidaySearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.planitsquaretest.public_.Tables.*;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HolidayQueryRepository {

    private final DSLContext dslContext;

    public List<HolidaySearchResponseDto> findAllHolidayByCondition(HolidaySearchConditionDto filter, Long countryId) {
        Condition condition = DSL.trueCondition();

        if (filter.year() != null) {
            LocalDate fromDate = LocalDate.of(filter.year(), 1, 1);
            LocalDate toDate = LocalDate.of(filter.year(), 12, 31);
            condition = condition.and(HOLIDAY.DATE.between(fromDate, toDate));
        }

        if (countryId != null) {
            condition = condition.and(HOLIDAY.COUNTRY_ID.eq(countryId));
        }

        return dslContext.select(
                        COUNTRY.NAME.as("countryName"),
                        HOLIDAY.DATE.as("holidayDate"),
                        HOLIDAY.NAME.as("holidayName"),
                        HOLIDAY.LOCAL_NAME.as("holidayLocalName"),
                        DSL.val(filter.countryCode()).as("countryCode"),
                        HOLIDAY.IS_GLOBAL.as("isGlobal"),
                        DSL.multiset(
                                DSL.select(
                                                HOLIDAY_DETAIL.TYPE.as("detailType"),
                                                HOLIDAY_DETAIL.CONTENT.as("value")
                                        )
                                        .from(HOLIDAY_DETAIL)
                                        .where(HOLIDAY_DETAIL.HOLIDAY_ID.eq(HOLIDAY.ID))
                        ).as("details").convertFrom(
                                data ->
                                        data.into(HolidaySearchResponseDto.HolidayDetailSearchResponseDto.class)
                        )
                )
                .from(HOLIDAY)
                .leftJoin(COUNTRY).on(HOLIDAY.COUNTRY_ID.eq(COUNTRY.ID))
                .where(condition)
                .offset(filter.offset() != null ? filter.offset() : 0)
                .limit(filter.limit() != null ? filter.limit() : 20)
                .fetchInto(HolidaySearchResponseDto.class);
    }
}
