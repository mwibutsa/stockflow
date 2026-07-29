package com.mwibutsa.stockflow.category;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toDto(Category category);

    Category toEntity(CategoryRequest payload);

    Category update(CategoryRequest payload, @MappingTarget Category category);
}
