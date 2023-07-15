package com.example.countryborders.controller;

import com.example.countryborders.exception.CountryNotFoundException;
import com.example.countryborders.exception.ErrorResult;
import com.example.countryborders.exception.RouteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { CountryNotFoundException.class, RouteNotFoundException.class})
    protected ErrorResult handleBadRequest(Exception ex, WebRequest request) {
        return new ErrorResult(ex.getMessage(), request.getDescription(false));
    }
}
