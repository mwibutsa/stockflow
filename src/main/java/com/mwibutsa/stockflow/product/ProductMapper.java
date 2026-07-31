package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.PaginatedResponse;
import com.mwibutsa.stockflow.common.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toDto(Product product);

    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest payload);

    default PaginatedResponse<ProductResponse> toPageResponse(Page<Product> pageEntity) {
        List<ProductResponse> content = pageEntity.getContent().stream().map(this::toDto).toList();
        PaginationDto metadata = new PaginationDto(
                pageEntity.getNumber(),
                pageEntity.getSize(),
                pageEntity.getTotalElements(),
                pageEntity.getTotalPages(),
                pageEntity.hasNext(),
                pageEntity.hasPrevious()
        );
        return new PaginatedResponse<>(content, metadata);
    }

    void update(UpdateProductRequest payload, @MappingTarget Product existingProduct);
}
