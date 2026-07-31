package com.mwibutsa.stockflow.supplier;

import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import com.mwibutsa.stockflow.product.Product;
import com.mwibutsa.stockflow.product.dto.UpdateProductRequest;
import com.mwibutsa.stockflow.supplier.dto.SupplierRequest;
import com.mwibutsa.stockflow.supplier.dto.SupplierResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends BaseMapper<Supplier, SupplierResponse>, ToEntityMapper<Supplier, SupplierRequest> {
    void update(UpdateProductRequest payload, @MappingTarget Product existingProduct);
}
