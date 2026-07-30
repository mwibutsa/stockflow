package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public ConflictException(String message, String field) {
        super(message, HttpStatus.CONFLICT, field);
    }

    public ConflictException() {
        super("Unique key violation", HttpStatus.CONFLICT);
    }
}
