package com.mwibutsa.stockflow.po.dto;

import com.mwibutsa.stockflow.po.PoStatus;
import com.mwibutsa.stockflow.supplier.dto.SupplierResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class PoResponse extends BasePoDto {
    private UUID string;
    private SupplierResponse supplier;
    private PoStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
