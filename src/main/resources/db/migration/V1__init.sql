CREATE TABLE country (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         code VARCHAR(255) NOT NULL,
                         created_at DATETIME(6),
                         modified_at DATETIME(6)
);

CREATE TABLE country_detail (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                country_id BIGINT NOT NULL,
                                type ENUM('GOOGLE_CALENDAR_ID') NOT NULL,
                                content VARCHAR(255),
                                description VARCHAR(255),
                                created_at DATETIME(6),
                                modified_at DATETIME(6),
                                CONSTRAINT fk_country_detail_country_id
                                    FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE holiday (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         country_id BIGINT NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         date DATE NOT NULL,
                         created_at DATETIME(6),
                         modified_at DATETIME(6),
                         CONSTRAINT fk_holiday_country
                             FOREIGN KEY (country_id) REFERENCES country(id)
);
