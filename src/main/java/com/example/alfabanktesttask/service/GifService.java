package com.example.alfabanktesttask.service;

import com.example.alfabanktesttask.DTO.GifDTO;
import com.example.alfabanktesttask.feign.GifClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifService {
    private final GifClient gifClient;

    public GifDTO getGifDTO(String tag) {
        // get link
        String gifLink = gifClient.getLink(tag);
        return new GifDTO(tag, gifLink);
    }
}
