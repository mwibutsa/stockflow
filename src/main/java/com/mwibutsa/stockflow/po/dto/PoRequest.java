package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class PoRequest extends BasePoDto {
    @NotBlank
    private UUID supplierId;
}
