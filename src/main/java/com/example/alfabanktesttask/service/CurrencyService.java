package com.example.alfabanktesttask.service;

import com.example.alfabanktesttask.exceptions.CurrencyNotFoundException;
import com.example.alfabanktesttask.feign.GifService;
import com.example.alfabanktesttask.feign.RateService;
import com.example.alfabanktesttask.properties.GiphyProperty;
import com.example.alfabanktesttask.properties.OXRProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import feign.FeignException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Getter
@Setter
@Service
public class CurrencyService {

    private final RateService rateService;
    private final GifService gifService;
    private double latest;
    private double yesterday;
    private double rubRate; //Курс рубля для дальнейшей конвертации
    private double rubRateYesterday; //Вчерашний курс рубля для дальнейшей конвертации
    private String currency = "RUB";
    private String response;
    private String resultTag;
    private String gifLink;
    private OXRProperty oXRProperty;
    private GiphyProperty giphyProperty;

    @Autowired
    public CurrencyService(RateService rateService, GifService gifService, OXRProperty oXRProperty, GiphyProperty giphyProperty) {
        this.rateService = rateService;
        this.gifService = gifService;
        this.oXRProperty = oXRProperty;
        this.giphyProperty = giphyProperty;
        setLatest(); // вычисляем курс рубля для верной конвертации
        setYesterday();
        setRubRate(getLatest());
        setRubRateYesterday(getYesterday());
    }

    public void calculate(String currency) {
        setCurrency(currency);
        setLatest();
        setYesterday();
        convert();
        compare();
    }

    public void setLatest() {
        try {
            String response = rateService.getLatest(oXRProperty.getAppId(), currency, oXRProperty.getBase());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class).getAsJsonObject("rates");
            if (jsonObject.get(currency) == null) {
                currency = "Error";
            } else {
                latest = jsonObject.get(currency).getAsDouble();
            }
        } catch (FeignException.BadRequest e) {
            throw new CurrencyNotFoundException();
        }
    }

    public void setYesterday() {
        try {
            String yesterdayDate = LocalDate.now().minusDays(1).toString();
            String response = rateService.getHistorical(oXRProperty.getAppId(), yesterdayDate, currency, oXRProperty.getBase());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class).getAsJsonObject("rates");
            if (jsonObject.get(currency) == null) {
                currency = "Error";
            } else {
                yesterday = jsonObject.get(currency).getAsDouble();
            }
        } catch (FeignException.BadRequest e) {
            throw new CurrencyNotFoundException();
        }
    }

    public void compare() {
        if (latest < yesterday) {
            resultTag = "broke";

        } else {
            resultTag = "rich";
        }
        response = gifService.get(giphyProperty.getApiKey(), resultTag);
    }


    public String getLink() {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        gifLink = jsonObject.getAsJsonObject("data").getAsJsonObject("images").getAsJsonObject("downsized").get("url").getAsString();
        return gifLink;
    }

    public void convert() {
        latest = rubRate / latest;
        yesterday = rubRateYesterday / yesterday;
    }



}
