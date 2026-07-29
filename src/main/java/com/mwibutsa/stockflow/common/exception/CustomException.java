package com.mwibutsa.stockflow.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus statusCode;

    // 1. Fully customized (message + status code)
    public CustomException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    // 2. Message only (defaults to 500 INTERNAL_SERVER_ERROR or 400 BAD_REQUEST)
    public CustomException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST; // or whatever default makes sense for you
    }

    public CustomException() {
        super("An unexpected error occurred");
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomException(HttpStatus statusCode) {
        super();
        this.statusCode = statusCode;
    }

}