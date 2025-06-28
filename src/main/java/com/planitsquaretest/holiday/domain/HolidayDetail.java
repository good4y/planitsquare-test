package com.planitsquaretest.holiday.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HolidayDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Holiday holiday;

    @Enumerated(EnumType.STRING)
    public SourceType sourceType;

    @Enumerated(EnumType.STRING)
    public HolidayDetailType type;

    public String content;

    @Builder
    private HolidayDetail(SourceType sourceType, HolidayDetailType type, String content) {
        this.sourceType = sourceType;
        this.type = type;
        this.content = content;
    }
}
