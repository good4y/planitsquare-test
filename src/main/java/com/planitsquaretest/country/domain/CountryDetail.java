package com.planitsquaretest.country.domain;

import com.planitsquaretest.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountryDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Enumerated(EnumType.STRING)
    private CountryDetailType type;

    private String content;

    private String description;

    @Builder
    private CountryDetail(String content, String description, CountryDetailType type) {
        this.content = content;
        this.description = description;
        this.type = type;
    }
}
