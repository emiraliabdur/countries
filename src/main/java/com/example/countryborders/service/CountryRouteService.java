package com.example.countryborders.service;

import com.example.countryborders.model.Route;

public interface CountryRouteService {

    Route findRouteViaBorders(String origCountryCode, String destCountryCode);
}
