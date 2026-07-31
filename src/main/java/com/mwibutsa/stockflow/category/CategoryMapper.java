package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.category.dto.CategoryRequest;
import com.mwibutsa.stockflow.category.dto.CategoryResponse;
import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryResponse>,
        ToEntityMapper<Category, CategoryRequest> {
}
