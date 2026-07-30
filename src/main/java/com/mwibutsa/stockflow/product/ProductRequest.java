package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.BaseProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductRequest extends BaseProductDto {
    private String categoryId;
}
