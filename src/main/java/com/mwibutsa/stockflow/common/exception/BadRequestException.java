package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException() {
        super("Bad request", HttpStatus.BAD_REQUEST);
    }
}
