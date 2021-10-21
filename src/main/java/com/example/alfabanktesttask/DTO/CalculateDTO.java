package com.example.alfabanktesttask.DTO;

import lombok.Data;

@Data
public class CalculateDTO {
    private final String currency;
    private final double latest;
    private final double yesterday;
    private final String tag;
    private final String link;

}
