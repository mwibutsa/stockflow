package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, String field) {
        super(message, HttpStatus.BAD_REQUEST, field);
    }

    public BadRequestException() {
        super("Bad request", HttpStatus.BAD_REQUEST);
    }
}
