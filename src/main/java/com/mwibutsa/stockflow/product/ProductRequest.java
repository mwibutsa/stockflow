package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.BaseProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductRequest extends BaseProductDto {
    @NotNull(message = "Category ID is required")
    private UUID categoryId;


    private String sku;
}
