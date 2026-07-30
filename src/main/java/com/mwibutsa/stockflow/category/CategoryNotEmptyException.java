package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CategoryNotEmptyException extends CustomException {
    public CategoryNotEmptyException() {
        super("Can not delete a category that has products.", HttpStatus.BAD_REQUEST);
    }
}
