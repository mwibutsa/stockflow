package com.mwibutsa.stockflow.inventory.dto;

import com.mwibutsa.stockflow.inventory.StockTransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BaseInventoryTransactionDto {
    @NotNull
    protected StockTransactionType type;
    @Positive
    protected Integer quantity;
    protected String reference;
    protected String notes;
}
