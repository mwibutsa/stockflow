package com.mwibutsa.stockflow.po.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PoProductDto {
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private String barcode;
}
