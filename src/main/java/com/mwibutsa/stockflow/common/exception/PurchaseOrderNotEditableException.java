package com.mwibutsa.stockflow.common.exception;

import org.springframework.http.HttpStatus;

public class PurchaseOrderNotEditableException extends CustomException {


    public PurchaseOrderNotEditableException() {
        super("Can not modify purchase order", HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
