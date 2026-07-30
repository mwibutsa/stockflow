package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.category.CategoryResponse;
import com.mwibutsa.stockflow.common.BaseProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResponse extends BaseProductDto {
    private UUID id;
    private String sku;
    private CategoryResponse category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
