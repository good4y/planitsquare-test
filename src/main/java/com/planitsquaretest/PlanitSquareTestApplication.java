package com.planitsquaretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class PlanitSquareTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanitSquareTestApplication.class, args);
    }

}
