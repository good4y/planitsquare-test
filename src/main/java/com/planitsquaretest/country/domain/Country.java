package com.planitsquaretest.country.domain;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private final List<CountryDetail> countryDetails = new ArrayList<>();

    @Builder
    private Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void addCountryDetail(CountryDetail detail) {
        this.countryDetails.add(detail);
        detail.setCountry(this);
    }
}
