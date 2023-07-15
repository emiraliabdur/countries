package com.example.countryborders.controller;

import com.example.countryborders.api.RoutingApi;
import com.example.countryborders.mapper.RouteMapper;
import com.example.countryborders.model.RouteDto;
import com.example.countryborders.service.CountryRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController implements RoutingApi {
    private final CountryRouteService countryRouteService;
    private final RouteMapper routeMapper;

    public CountryController(CountryRouteService countryRouteService, RouteMapper routeMapper) {
        this.countryRouteService = countryRouteService;
        this.routeMapper = routeMapper;
    }

    @Override
    public ResponseEntity<RouteDto> routingOriginDestinationGet(String origin, String destination) {
        var route = countryRouteService.findRouteViaBorders(origin, destination);

        return ResponseEntity.ok(routeMapper.toDto(route));
    }
}
