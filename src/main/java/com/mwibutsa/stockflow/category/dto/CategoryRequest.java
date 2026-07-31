package com.mwibutsa.stockflow.category.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRequest {
    @NotEmpty
    @NotNull
    private String name;
    private String description;

}
