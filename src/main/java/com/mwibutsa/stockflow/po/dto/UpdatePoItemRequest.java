package com.mwibutsa.stockflow.po.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatePoItemRequest extends PoItemRequest {
    private UUID id;
}
