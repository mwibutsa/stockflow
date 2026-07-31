package com.mwibutsa.stockflow.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class InventoryProductDto {
    private UUID id;
    private String name;
    private String sku;
    private String barcode;
    private BigDecimal price;
    private BigDecimal costPrice;
}
