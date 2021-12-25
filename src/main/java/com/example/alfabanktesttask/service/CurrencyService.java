package com.example.alfabanktesttask.service;

import com.example.alfabanktesttask.DTO.ExchangeRateDTO;
import com.example.alfabanktesttask.exceptions.CurrencyNotFoundException;
import com.example.alfabanktesttask.feign.RateClient;
import com.example.alfabanktesttask.properties.OXRProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RateClient rateClient;
    private final OXRProperty oXRProperty;


    public ExchangeRateDTO getExchangeRateHistorical(String base, String symbols, LocalDate date) {
        try {
            if (base.equals(oXRProperty.getBase())) {
                return getExchangeRateHistorical(symbols, date);
            }
            ExchangeRateDTO baseExchangeRateDTO = getExchangeRateHistorical(base, date);
            ExchangeRateDTO symbolsExchangeRateDTO = getExchangeRateHistorical(symbols, date);
            return new ExchangeRateDTO(baseExchangeRateDTO.getLatest()
                    / symbolsExchangeRateDTO.getLatest(), base, symbols, date);
        } catch (FeignException.BadRequest e) {
            throw new CurrencyNotFoundException();
        }
    }

    private ExchangeRateDTO getExchangeRateHistorical(String symbols, LocalDate date) {
        String response = rateClient.getHistorical
                (oXRProperty.getAppId(), date.toString(), symbols, oXRProperty.getBase());
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class).getAsJsonObject("rates");

        if (jsonObject.get(symbols) == null) {
            throw new CurrencyNotFoundException();
        }
        return new ExchangeRateDTO(jsonObject.get(symbols).getAsDouble(), oXRProperty.getBase(), symbols, date);
    }


}