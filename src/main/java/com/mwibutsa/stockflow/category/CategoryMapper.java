package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.category.dto.CategoryRequest;
import com.mwibutsa.stockflow.category.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toDto(Category category);

    Category toEntity(CategoryRequest payload);

    Category update(CategoryRequest payload, @MappingTarget Category category);
}
