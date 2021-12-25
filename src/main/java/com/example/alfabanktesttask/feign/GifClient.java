package com.example.alfabanktesttask.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-microervice", url = "${gif-microservice.url}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET)
    String getLink(@RequestParam String tag);
}
