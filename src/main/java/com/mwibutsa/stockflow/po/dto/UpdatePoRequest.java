package com.mwibutsa.stockflow.po.dto;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatePoRequest extends PoRequest {
    @Valid
    private List<UpdatePoItemRequest> items = new ArrayList<>();
}
