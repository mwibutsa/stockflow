package com.mwibutsa.stockflow.po.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PoItemResponse extends BasePoItemDto {
    private UUID id;
    private PoProductDto product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
