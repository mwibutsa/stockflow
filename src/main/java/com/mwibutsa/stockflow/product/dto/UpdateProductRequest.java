package com.mwibutsa.stockflow.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductRequest extends BaseProductDto {
    private UUID categoryId;

    @NotBlank
    private String sku;
}
