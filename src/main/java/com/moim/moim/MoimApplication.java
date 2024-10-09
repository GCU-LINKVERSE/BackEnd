package com.moim.moim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoimApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoimApplication.class, args);
    }

}
