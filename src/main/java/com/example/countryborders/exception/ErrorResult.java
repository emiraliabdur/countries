package com.example.countryborders.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResult {
    private String message;
    private String requestDescription;
}
