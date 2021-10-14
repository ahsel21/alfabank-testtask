package com.example.alfabanktesttask.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CurrencyServiceTest {


    @Autowired
    CurrencyService currencyService;

    @Test
    public void testSetCurrency() {
        currencyService.setCurrency("USD");
        Assertions.assertEquals("USD", currencyService.getCurrency());
    }

    @Test
    public void testConvertEUR() {
        currencyService.setYesterday(0.75);
        currencyService.setLatest(0.8);
        currencyService.setRubRateYesterday(72);
        currencyService.setRubRate(72);
        currencyService.convert();
        Assertions.assertEquals(90, currencyService.getLatest());
        Assertions.assertEquals(96, currencyService.getYesterday());
    }

}