package com.mwibutsa.stockflow.common.mapper;

import org.mapstruct.MappingTarget;

public interface ToEntityMapper<Entity, Payload> {
    Entity toEntity(Payload payload);

    void update(Payload payload, @MappingTarget Entity category);
}
