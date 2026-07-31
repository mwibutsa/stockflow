package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.product.dto.ProductRequest;
import com.mwibutsa.stockflow.product.dto.ProductResponse;
import com.mwibutsa.stockflow.product.dto.UpdateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductResponse> {
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "stockQuantity", defaultValue = "0")
    Product toEntity(ProductRequest payload);


    void update(UpdateProductRequest payload, @MappingTarget Product existingProduct);
}
