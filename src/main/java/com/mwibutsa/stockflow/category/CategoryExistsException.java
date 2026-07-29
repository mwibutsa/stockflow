package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CategoryExistsException extends CustomException {
    public CategoryExistsException() {
        super("Category already exist", HttpStatus.CONFLICT);
    }
}
