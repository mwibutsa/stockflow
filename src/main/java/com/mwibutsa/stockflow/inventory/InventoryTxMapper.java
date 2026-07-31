package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.dto.PaginationDto;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionRequest;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryTxMapper {
    InventoryTransactionResponse toDto(InventoryTransaction inventoryTransaction);

    InventoryTransaction toEntity(InventoryTransactionRequest payload);

    default PaginatedResponse<InventoryTransactionResponse> toPageResponse(Page<InventoryTransaction> pageEntity) {
        List<InventoryTransactionResponse> content = pageEntity.getContent().stream().map(this::toDto).toList();
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
