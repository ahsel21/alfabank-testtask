package com.example.alfabanktesttask.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "calculate")
@Getter
@Setter
@Validated
public class CalculateProperty {
    private String base;
}
