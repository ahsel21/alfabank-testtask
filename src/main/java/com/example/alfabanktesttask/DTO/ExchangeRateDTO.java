package com.example.alfabanktesttask.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRateDTO {
    private final double latest;
    private final String baseCurrency;
    private final String quoteCurrency;
    private final LocalDate date;

}
