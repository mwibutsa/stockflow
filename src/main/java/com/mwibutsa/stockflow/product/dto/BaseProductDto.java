package com.mwibutsa.stockflow.product.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseProductDto {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank
    private String unit = "pcs";

    @NotBlank(message = "Barcode is required")
    private String barcode;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    @NotNull(message = "Cost price is required")
    @PositiveOrZero(message = "Cost price must be zero or greater")
    @Digits(integer = 10, fraction = 2, message = "Invalid cost price format")
    private BigDecimal costPrice;

    private String description;

    @Min(value = 0, message = "Minimum inventory level cannot be negative")
    private Integer stockQuantity = 0;

    @Min(value = 0, message = "Minimum inventory level cannot be negative")
    private Integer minStockLevel = 0;
}
