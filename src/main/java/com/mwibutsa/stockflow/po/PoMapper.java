package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import com.mwibutsa.stockflow.po.dto.PoItemRequest;
import com.mwibutsa.stockflow.po.dto.PoRequest;
import com.mwibutsa.stockflow.po.dto.PoResponse;
import com.mwibutsa.stockflow.po.dto.UpdatePoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PoMapper extends BaseMapper<Po, PoResponse>, ToEntityMapper<Po, PoRequest> {
    Po toEntity(PoRequest payload);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "purchaseOrder", ignore = true)
    PoItem toEntity(PoItemRequest payload);

    void update(PoItemRequest payload, @MappingTarget PoItem item);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "supplier")
    @Mapping(ignore = true, target = "items")
    @Mapping(target = "status", ignore = true)
    Po update(UpdatePoRequest payload, @MappingTarget Po po);
}
