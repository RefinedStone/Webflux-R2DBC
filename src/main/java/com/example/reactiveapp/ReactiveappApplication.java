package com.example.reactiveapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.reactiveapp")
public class ReactiveappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveappApplication.class, args);
    }

}
