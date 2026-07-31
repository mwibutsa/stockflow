package com.mwibutsa.stockflow.common.mapper;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.dto.PaginationDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<E, D> {

    D toDto(E entity);

    default PaginatedResponse<D> toPageResponse(Page<E> pageEntity) {
        List<D> content = pageEntity.getContent().stream().map(this::toDto).toList();

        PaginationDto metadata = new PaginationDto(
                pageEntity.getNumber() + 1,
                pageEntity.getSize(),
                pageEntity.getTotalElements(),
                pageEntity.getTotalPages(),
                pageEntity.hasNext(),
                pageEntity.hasPrevious()
        );

        return new PaginatedResponse<>(content, metadata);
    }
}