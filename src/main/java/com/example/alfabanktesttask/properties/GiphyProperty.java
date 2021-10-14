package com.example.alfabanktesttask.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "giphy")
@Getter
@Setter
@Validated
public class GiphyProperty {
    @NotBlank
    private String apiKey;
    @URL
    private String url;
}
