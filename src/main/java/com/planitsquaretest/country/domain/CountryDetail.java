package com.planitsquaretest.country.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountryDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @Enumerated(EnumType.STRING)
    private CountryDetailType type;

    private String content;

    private String description;

    @Builder
    private CountryDetail(String content, Country country, String description, CountryDetailType type) {
        this.content = content;
        this.country = country;
        this.description = description;
        this.type = type;
    }
}
