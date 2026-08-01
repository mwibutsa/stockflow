package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class PurchaseOrderNotFoundException extends CustomException {

    public PurchaseOrderNotFoundException() {
        super("Purchase order not found", HttpStatus.NOT_FOUND);
    }
}
