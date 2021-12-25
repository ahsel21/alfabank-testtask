package com.example.alfabanktesttask.controllers;

import com.example.alfabanktesttask.DTO.CalculateDTO;
import com.example.alfabanktesttask.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RefreshScope
public class MainController {
    private final CalculateService calculateService;

    @RequestMapping("/")
    public String welcome() {
        return "home";
    }

    @Value("${message: Default message}")
    private String message;

    @PostMapping(path = "/rate")
    public String count(@RequestParam String inputCurrency, Model model) {
        CalculateDTO calculateDTO = calculateService.calculate(inputCurrency);
        model.addAttribute("calculateDTO", calculateDTO);
        //model.addAttribute("quote", message);
        return "rate";
    }
}
