package com.example.alfabanktesttask.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "home";
    }
}
