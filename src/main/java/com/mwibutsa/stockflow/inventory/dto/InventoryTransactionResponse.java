package com.mwibutsa.stockflow.inventory.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryTransactionResponse extends BaseInventoryTransactionDto {
    private UUID id;
    private InventoryProductDto product;
    private Integer previousStock;
    private Integer newStock;
    private LocalDateTime createdAt;
}
