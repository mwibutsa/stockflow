package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReceivePoRequest {
    @Valid
    private List<ReceivePoItemRequest> items = new ArrayList<>();
}
