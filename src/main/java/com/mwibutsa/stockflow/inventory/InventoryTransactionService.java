package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.exception.BadRequestException;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionRequest;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionResponse;
import com.mwibutsa.stockflow.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryTransactionService {

    private final InventoryRepository inventoryRepository;
    private final InventoryTxMapper inventoryTxMapper;
    private final ProductRepository productRepository;

    public PaginatedResponse<InventoryTransactionResponse> getAllTransactions(String search, Pageable pagination, UUID productId) {
        Specification<InventoryTransaction> spec = Specification
                .where(InventoryTxSpecification.hasProductId(productId))
                .and(InventoryTxSpecification.searchTransactions(search));
        return inventoryTxMapper.toPageResponse(inventoryRepository.findAll(spec, pagination));
    }

    @Transactional
    public InventoryTransactionResponse createNewTransaction(InventoryTransactionRequest payload) {
        var product = productRepository.findById(payload.getProductId())
                .orElseThrow(() -> new BadRequestException("Invalid product", "productId"));


        var currentQuantity = product.getStockQuantity();


        Integer newStock = getNewStock(payload, currentQuantity);

        // update product stock quantity
        product.setStockQuantity(newStock);
        productRepository.save(product);

        // create stock transaction
        var newTx = inventoryTxMapper.toEntity(payload);
        newTx.setPreviousStock(currentQuantity);
        newTx.setNewStock(newStock);
        newTx.setProduct(product);

        inventoryRepository.save(newTx);
        return inventoryTxMapper.toDto(newTx);
    }

    private Integer getNewStock(InventoryTransactionRequest payload, Integer currentQuantity) {
        Integer newStock;

        switch (payload.getType()) {
            case STOCK_IN -> {
                newStock = currentQuantity + payload.getQuantity();
            }
            case STOCK_OUT -> {
                if (currentQuantity < payload.getQuantity()) {
                    throw new BadRequestException("Insufficient stock", "quantity");
                }
                newStock = currentQuantity - payload.getQuantity();
            }
            case ADJUSTMENT -> {
                newStock = payload.getQuantity();
            }
            default -> throw new BadRequestException("Invalid stock transaction type", "type");
        }
        return newStock;
    }
}
