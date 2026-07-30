package com.mwibutsa.stockflow.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    protected final HttpStatus statusCode;
    protected final String field;


    // 1. Fully customized (message + status code)
    public CustomException(String message, HttpStatus statusCode, String field) {
        super(message);
        this.statusCode = statusCode;
        this.field = field;
    }

    // 2. Message only (defaults to 500 INTERNAL_SERVER_ERROR or 400 BAD_REQUEST)
    public CustomException(String message, String field) {
        super(message);
        this.field = field;
        this.statusCode = HttpStatus.BAD_REQUEST; // or whatever default makes sense for you
    }

    public CustomException(HttpStatus statusCode, String field) {
        super();
        this.statusCode = statusCode;
        this.field = field;
    }

    public CustomException(String message, HttpStatus statusCode) {
        super();
        this.statusCode = statusCode;
        this.field = null;
    }
}