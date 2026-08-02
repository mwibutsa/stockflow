package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class ReceivePoItemRequest {
    @NotNull
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    @Positive
    private Integer quantityReceived;
}
