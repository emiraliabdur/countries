package com.example.countryborders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Route {
    private Country origin;
    private Country destination;
    private List<String> routes;
}
