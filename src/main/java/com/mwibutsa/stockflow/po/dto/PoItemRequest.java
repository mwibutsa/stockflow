package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class PoItemRequest extends BasePoItemDto {
    @NotBlank
    private UUID productId;
}
