package com.example.alfabanktesttask.service;

import com.example.alfabanktesttask.DTO.GifDTO;
import com.example.alfabanktesttask.feign.GifClient;
import com.example.alfabanktesttask.properties.GiphyProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifService {
    private final GifClient gifClient;
    private final GiphyProperty giphyProperty;


    public GifDTO getGifDTO(String tag) {
        // get link
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(gifClient.get(giphyProperty.getApiKey(), tag), JsonObject.class);
        String gifLink = jsonObject.getAsJsonObject("data").getAsJsonObject("images").getAsJsonObject("downsized").get("url").getAsString();
        return new GifDTO(tag, gifLink);
    }


}
