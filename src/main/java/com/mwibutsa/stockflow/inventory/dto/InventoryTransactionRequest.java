package com.mwibutsa.stockflow.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InventoryTransactionRequest extends BaseInventoryTransactionDto {
    @NotNull
    private UUID productId;

    @NotNull
    @Positive
    private Integer quantity;
}
