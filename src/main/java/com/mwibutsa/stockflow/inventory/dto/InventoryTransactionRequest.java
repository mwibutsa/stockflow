package com.mwibutsa.stockflow.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransactionRequest extends BaseInventoryTransactionDto {
    @NotNull
    private UUID productId;

    @NotNull
    @Positive
    private Integer quantity;
}
