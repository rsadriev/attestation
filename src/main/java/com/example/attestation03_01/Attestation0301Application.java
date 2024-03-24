package com.example.attestation03_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@SpringBootApplication
public class Attestation0301Application {

    public static void main(String[] args) {
        SpringApplication.run(Attestation0301Application.class, args);
    }

}
