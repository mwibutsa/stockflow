package com.mwibutsa.stockflow.po.dto;

import com.mwibutsa.stockflow.po.PoStatus;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private PoStatus status;
}
