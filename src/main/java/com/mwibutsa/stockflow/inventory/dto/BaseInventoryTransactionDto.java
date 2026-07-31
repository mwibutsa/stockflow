package com.mwibutsa.stockflow.inventory.dto;

import com.mwibutsa.stockflow.inventory.StockTransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BaseInventoryTransactionDto {
    @NotNull
    private StockTransactionType type;
    @Positive
    private Integer quantity;
    private String reference;
    private String notes;
}
