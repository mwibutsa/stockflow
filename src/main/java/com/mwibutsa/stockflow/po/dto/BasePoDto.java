package com.mwibutsa.stockflow.po.dto;

import com.mwibutsa.stockflow.po.PoStatus;
import lombok.Data;

@Data
public class BasePoDto {
    private String notes;
    private PoStatus status = PoStatus.PENDING;
}
