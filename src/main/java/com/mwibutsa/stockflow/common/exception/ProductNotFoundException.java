package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomException {
    public ProductNotFoundException() {
        super("Product not found", HttpStatus.NOT_FOUND);
    }
    
}
