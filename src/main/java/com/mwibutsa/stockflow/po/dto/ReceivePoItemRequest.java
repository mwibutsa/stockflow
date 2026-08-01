package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReceivePoItemRequest extends PoItemRequest {
    @NotNull
    private UUID id;

    @NotNull
    private Integer receivedQuantity;
}
