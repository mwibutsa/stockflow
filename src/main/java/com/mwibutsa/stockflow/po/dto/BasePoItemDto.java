package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class BasePoItemDto {
    @Positive
    private Integer quantityOrdered;

    @PositiveOrZero
    private Integer quantityReceived = 0;

    @Positive
    private Integer unitCost;
}
