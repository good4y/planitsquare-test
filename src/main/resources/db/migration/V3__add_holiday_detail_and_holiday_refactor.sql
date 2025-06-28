-- holiday detail 테이블 추가
CREATE TABLE holiday_detail
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    holiday_id  BIGINT                        NOT NULL,
    source_type ENUM ('GOOGLE', 'NAGER')      NOT NULL,
    type        ENUM ('COUNTY', 'LAUNCHYEAR', 'LOCAL_NAME') NOT NULL,
    content       VARCHAR(255)                  NOT NULL,
    created_at  DATETIME(6),
    modified_at DATETIME(6),
    CONSTRAINT fk_holiday_detail_holiday_id
        FOREIGN KEY (holiday_id) REFERENCES holiday (id) ON DELETE CASCADE
);

-- holiday type 테이블 추가
CREATE TABLE holiday_type (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              type ENUM('PUBLIC', 'SCHOOL', 'AUTHORITIES', 'BANK', 'OPTIONAL', 'OBSERVANCE', 'DEFAULT') NOT NULL UNIQUE,
                              description VARCHAR(255),
                              created_at DATETIME(6),
                              modified_at DATETIME(6)
);

-- holiday type 중간테이블 추가
CREATE TABLE holiday_type_map (
                                  holiday_id BIGINT NOT NULL,
                                  holiday_type_id BIGINT NOT NULL,
                                  PRIMARY KEY (holiday_id, holiday_type_id),
                                  CONSTRAINT fk_holiday_type_map_holiday_id
                                      FOREIGN KEY (holiday_id) REFERENCES holiday(id) ON DELETE CASCADE,
                                  CONSTRAINT fk_holiday_type_map_holiday_type_id
                                      FOREIGN KEY (holiday_type_id) REFERENCES holiday_type(id) ON DELETE CASCADE
);

ALTER TABLE holiday_detail
    ADD CONSTRAINT uk_holiday_detail_holiday_id_type_source_type UNIQUE (holiday_id, type, source_type);

-- holiday 테이블 수정
ALTER TABLE holiday
    ADD COLUMN is_global BOOLEAN DEFAULT TRUE AFTER date;

CREATE INDEX idx_holiday_country_date ON holiday (country_id, date);

-- Country detail 테이블에 cascade 추가
ALTER TABLE country_detail DROP CONSTRAINT fk_country_detail_country_id;

ALTER TABLE country_detail
    ADD CONSTRAINT fk_country_detail_country_id
        FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE;