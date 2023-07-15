package com.example.countryborders.client.mledoze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryDto {
    private String cca3;
    private String region;
    private List<String> borders;
}
