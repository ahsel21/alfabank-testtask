package com.example.alfabanktesttask.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rateService", url = "${oxr.url}")
public interface RateClient {
    @RequestMapping(method = RequestMethod.GET, path = "historical/{date}.json")
    String getHistorical(@RequestParam String app_id, @PathVariable String date,
                         @RequestParam String symbols, @RequestParam String base);

}
