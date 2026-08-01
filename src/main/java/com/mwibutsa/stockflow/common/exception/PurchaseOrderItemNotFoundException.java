package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class PurchaseOrderItemNotFoundException extends CustomException {
    public PurchaseOrderItemNotFoundException() {
        super("Purchase order item not found", HttpStatus.NOT_FOUND);
    }
}
