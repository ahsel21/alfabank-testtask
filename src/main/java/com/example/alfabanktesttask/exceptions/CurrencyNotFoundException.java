package com.example.alfabanktesttask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Wrong currency code!")
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException() {
        super("Currency not found!");
    }
}