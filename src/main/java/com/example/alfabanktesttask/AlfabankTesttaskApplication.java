package com.example.alfabanktesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan("com.example.alfabanktesttask.properties")
public class AlfabankTesttaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlfabankTesttaskApplication.class, args);
    }
}
