package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionRequest;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryTxMapper extends BaseMapper<InventoryTransaction,
        InventoryTransactionResponse>, ToEntityMapper<InventoryTransaction, InventoryTransactionRequest> {
}
