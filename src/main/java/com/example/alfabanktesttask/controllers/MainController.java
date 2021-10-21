package com.example.alfabanktesttask.controllers;

import com.example.alfabanktesttask.DTO.CalculateDTO;
import com.example.alfabanktesttask.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final CalculateService calculateService;

    @RequestMapping("/")
    public String welcome() {
        return "home";
    }

    @PostMapping(path = "/rate")
    public String count(@RequestParam String inputCurrency, Model model) {
        CalculateDTO calculateDTO = calculateService.calculate(inputCurrency);
        model.addAttribute("calculateDTO", calculateDTO);
        return "rate";
    }
}
