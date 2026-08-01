package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasePoItemDto {
    @NotNull
    @Positive
    protected Integer quantityOrdered;

    @NotNull
    @Positive
    protected BigDecimal unitCost;
}
