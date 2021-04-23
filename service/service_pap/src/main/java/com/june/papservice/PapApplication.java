package com.june.papservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.june"})
public class PapApplication {
    public static void main(String[] args) {
        SpringApplication.run(PapApplication.class, args);
    }
}
