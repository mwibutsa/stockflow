package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
public class PoItemRequest extends BasePoItemDto {
    @NotNull
    private UUID productId;

    @PositiveOrZero
    protected Integer quantityReceived = 0;
}
