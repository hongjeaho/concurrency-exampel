package com.example.concurrencyexampel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ConcurrencyExampelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyExampelApplication.class, args);
    }

}
