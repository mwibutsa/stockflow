package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class SupplierNotFoundException extends CustomException {
    public SupplierNotFoundException() {
        super("Supplier not found", HttpStatus.NOT_FOUND);
    }
}
