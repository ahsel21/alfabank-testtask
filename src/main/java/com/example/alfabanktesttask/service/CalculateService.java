package com.example.alfabanktesttask.service;

import com.example.alfabanktesttask.DTO.CalculateDTO;
import com.example.alfabanktesttask.properties.CalculateProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class CalculateService {
    private final CurrencyService currencyService;
    private final GifService gifService;
    private final CalculateProperty calculateProperty;

    public CalculateDTO calculate(String currency) {
        double latest = currencyService.getExchangeRateHistorical(calculateProperty.getBase(), currency, LocalDate.now(ZoneId.of("UTC"))).getLatest();
        double yesterday = currencyService.getExchangeRateHistorical(calculateProperty.getBase(), currency, LocalDate.now(ZoneId.of("UTC")).minusDays(1)).getLatest();
        String tag = latest < yesterday ? "broke" : "rich";
        String gifLink = gifService.getGifDTO(tag).getGifLink();
        return new CalculateDTO(currency, latest, yesterday, tag, gifLink);
    }
}
