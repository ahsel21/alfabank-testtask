package com.example.alfabanktesttask.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "oxr")
@Getter
@Setter
@Validated
public class OXRProperty {
    @NotBlank
    private String appId;
    @URL
    private String url;
    private String base;


}
