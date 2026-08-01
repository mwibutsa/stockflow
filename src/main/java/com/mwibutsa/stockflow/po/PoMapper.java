package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import com.mwibutsa.stockflow.po.dto.PoItemRequest;
import com.mwibutsa.stockflow.po.dto.PoRequest;
import com.mwibutsa.stockflow.po.dto.PoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoMapper extends BaseMapper<Po, PoResponse>, ToEntityMapper<Po, PoRequest> {
    Po toEntity(PoRequest payload);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "purchaseOrder", ignore = true)
    PoItem toEntity(PoItemRequest payload);
}
