package com.example.alfabanktesttask.controllers;

import com.example.alfabanktesttask.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CurrencyService currencyService;

    @RequestMapping("/")
    public String welcome() {
        return "home";
    }

    @PostMapping(path = "/rate")
    public String count(@RequestParam String inputCurrency, Model model) {
        currencyService.calculate(inputCurrency);
        if (inputCurrency.equals(currencyService.getCurrency())) {
            model.addAttribute("link", currencyService.getLink());
            model.addAttribute("currency", inputCurrency);
            model.addAttribute("now", currencyService.getLatest());
            model.addAttribute("yesterday", currencyService.getYesterday());
            model.addAttribute("tag", currencyService.getResultTag());
            return "rate";
        }
        return "home";
    }
}
