package com.mwibutsa.stockflow.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseProductDto {
    private String name;
    private String unit;
    private String barcode;
    private BigDecimal price;
    private BigDecimal costPrice;
    private String description;
    private Integer stockQuantity;
    private Integer minStockLevel;
}
