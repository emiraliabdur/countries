package com.example.countryborders.exception;

public class CountryNotFoundException extends RuntimeException {
    private static final String ERROR_MSG = "Country with code %s is missing";
    private final String countryCode;

    public CountryNotFoundException(String countryCode) {
        super(String.format(ERROR_MSG, countryCode));
        this.countryCode = countryCode;
    }
}
