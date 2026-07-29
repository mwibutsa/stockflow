package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.common.exception.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CategoryNotFoundException extends CustomException {
    public CategoryNotFoundException() {
        super("Category not found.", HttpStatus.NOT_FOUND);
    }
}
