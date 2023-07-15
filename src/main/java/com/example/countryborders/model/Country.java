package com.example.countryborders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {

    private String code;
    private Region region;
    private List<String> borders;
}
