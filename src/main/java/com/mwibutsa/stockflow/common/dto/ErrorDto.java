package com.mwibutsa.stockflow.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String error;
    private String field;

    public ErrorDto(String error) {
        this.error = error;
    }
}
