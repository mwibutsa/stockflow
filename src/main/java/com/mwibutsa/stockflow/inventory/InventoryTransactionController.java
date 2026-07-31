package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionRequest;
import com.mwibutsa.stockflow.inventory.dto.InventoryTransactionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryTransactionController {

    private final InventoryTransactionService inventoryTransactionService;

    @GetMapping
    public PaginatedResponse<InventoryTransactionResponse> getStockTransactions(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(required = false) UUID productId
    ) {
        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);
        // Pageable
        Pageable pagination = PageRequest.of(Math.max(page, 1) - 1, size, Sort.by(sortDir, sortBy));
        return inventoryTransactionService.getAllTransactions(search, pagination, productId);
    }

    @PostMapping
    public ResponseEntity<InventoryTransactionResponse> createNewTransaction(
            @Valid @RequestBody InventoryTransactionRequest payload,
            UriComponentsBuilder uriBuilder
    ) {
        var inventoryTx = inventoryTransactionService.createNewTransaction(payload);
        var uri = uriBuilder.path("/inventory/{inventoryId}").buildAndExpand(inventoryTx.getId()).toUri();
        return ResponseEntity.created(uri).body(inventoryTx);
    }
}
