package com.example.alfabanktesttask.service;


import com.example.alfabanktesttask.DTO.CalculateDTO;
import com.example.alfabanktesttask.DTO.ExchangeRateDTO;
import com.example.alfabanktesttask.DTO.GifDTO;
import com.example.alfabanktesttask.properties.CalculateProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.ZoneId;


@SpringBootTest(classes = CalculateService.class)
@EnableConfigurationProperties(CalculateProperty.class)
public class ServiceTest {
    @MockBean
    GifService mockGifService;

    @MockBean
    CurrencyService mockCurrencyService;

    @Autowired
    CalculateService calculateService;

    @Test
    void getGifDTO() {
        String tag = "rich";
        String link = "https://test.link/";

        Mockito.when(mockGifService.getGifDTO(tag)).thenReturn(new GifDTO(tag, link));

        GifDTO expected = new GifDTO(tag, link);

        Assertions.assertEquals(expected, mockGifService.getGifDTO(tag));
    }

    @Test
    void getExchangeRateDTO() {
        String baseCurrency = "RUB";
        String quoteCurrency = "USD";
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        double latest = 2.0;

        Mockito.when(mockCurrencyService.getExchangeRateHistorical(baseCurrency, quoteCurrency,
                date)).thenReturn(new ExchangeRateDTO(latest, baseCurrency, quoteCurrency, date));

        ExchangeRateDTO expected = new ExchangeRateDTO(latest, baseCurrency, quoteCurrency, date);

        Assertions.assertEquals(expected, mockCurrencyService.getExchangeRateHistorical(baseCurrency, quoteCurrency, date));
    }

    @Test
    void getCalculateDTO() {
        String baseCurrency = "RUB";
        String quoteCurrency = "USD";
        LocalDate date = LocalDate.now(ZoneId.of("UTC"));
        double latest = 2.0;
        double yesterday = 1.0;
        String tag = "rich";
        String link = "https://test.link/";

        Mockito.when(mockCurrencyService.getExchangeRateHistorical(baseCurrency, quoteCurrency,
                date.minusDays(1))).thenReturn(new ExchangeRateDTO(yesterday, baseCurrency, quoteCurrency, date.minusDays(1)));

        Mockito.when(mockCurrencyService.getExchangeRateHistorical(baseCurrency, quoteCurrency,
                date)).thenReturn(new ExchangeRateDTO(latest, baseCurrency, quoteCurrency, date));

        Mockito.when(mockGifService.getGifDTO(tag)).thenReturn(new GifDTO(tag, link));

        CalculateDTO expected = new CalculateDTO(quoteCurrency, latest, yesterday, tag, link);

        Assertions.assertEquals(expected, calculateService.calculate(quoteCurrency));
    }

}