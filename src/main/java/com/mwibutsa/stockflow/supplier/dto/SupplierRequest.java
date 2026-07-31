package com.mwibutsa.stockflow.supplier.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SupplierRequest extends BaseSupplierDto {
    private Boolean isActive = true;
}
