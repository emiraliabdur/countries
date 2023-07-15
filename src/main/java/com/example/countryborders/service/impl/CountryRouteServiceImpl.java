package com.example.countryborders.service.impl;

import com.example.countryborders.exception.CountryNotFoundException;
import com.example.countryborders.exception.RouteNotFoundException;
import com.example.countryborders.model.Country;
import com.example.countryborders.model.Route;
import com.example.countryborders.service.CountryProviderService;
import com.example.countryborders.service.CountryRouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryRouteServiceImpl implements CountryRouteService {

    private final CountryProviderService countryProviderService;

    public CountryRouteServiceImpl(CountryProviderService countryProviderService) {
        this.countryProviderService = countryProviderService;
    }

    @Override
    public Route findRouteViaBorders(String origCountryCode, String destCountryCode) {
        var countryGraph = countryProviderService.obtainCountryGraph();
        var origin = countryGraph.getCountry(origCountryCode.toUpperCase());
        if (origin == null) {
            throw new CountryNotFoundException(origCountryCode);
        }
        var destination = countryGraph.getCountry(destCountryCode.toUpperCase());
        if (destination == null) {
            throw new CountryNotFoundException(destCountryCode);
        }

        if (!hasBorders(origin) || !hasBorders(destination) || !regionsHaveBorders(origin, destination)) {
            throw new RouteNotFoundException(origCountryCode, destCountryCode);
        }

        List<String> countriesRoute = countryGraph.findRoute(origin, destination);
        return new Route(origin, destination, countriesRoute);
    }

    private boolean regionsHaveBorders(Country origin, Country destination) {
        return origin.getRegion().connectedByLandWith(destination.getRegion());
    }

    private boolean hasBorders(Country country) {
        return !country.getBorders().isEmpty();
    }
}
