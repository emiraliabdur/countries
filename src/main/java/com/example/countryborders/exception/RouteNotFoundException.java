package com.example.countryborders.exception;

public class RouteNotFoundException extends RuntimeException {
    private static final String ERROR_MSG = "Route from country %s to %s is not found";
    private final String originCountryCode;
    private final String destinationCountryCode;

    public RouteNotFoundException(String originCountryCode, String destinationCountryCode) {
        super(String.format(ERROR_MSG, originCountryCode, destinationCountryCode));
        this.originCountryCode = originCountryCode;
        this.destinationCountryCode = destinationCountryCode;
    }
}
