package com.mwibutsa.stockflow.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryTransactionRequest extends BaseInventoryTransactionDto {
    @NotNull
    private UUID productId;

    @NotNull
    @Positive
    private Integer quantity;
}
