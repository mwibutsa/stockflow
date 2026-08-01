package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.po.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/purchase-orders")
public class PoController {
    private final PoService poService;

    @GetMapping
    public PaginatedResponse<PoResponse> getPurchaseOrders(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String direction,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) UUID supplierId
    ) {

        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);
        Pageable pagination = PageRequest.of(Math.max(page, 1) - 1, size, Sort.by(sortDir, sortBy));
        return poService.getPurchaseOrders(search, pagination, PoStatus.fromString(status), supplierId);
    }

    @PostMapping
    public ResponseEntity<PoResponse> createPurchaseOrder(@Valid @RequestBody PoRequest payload, UriComponentsBuilder uriBuilder) {
        var po = poService.createPurchaseOrder(payload);
        var uri = uriBuilder.path("/purchase-orders/{id}").buildAndExpand(po.getId()).toUri();

        return ResponseEntity.created(uri).body(po);
    }

    @GetMapping("/{poId}")
    public PoResponse getPurchaseOrder(@PathVariable UUID poId) {
        return this.poService.getPurchaseOrder(poId);
    }

    @PutMapping("/{poId}")
    public PoResponse updatePurchaseOrder(@PathVariable UUID poId, @Valid @RequestBody UpdatePoRequest payload) {
        return this.poService.updatePurchaseOrder(poId, payload);
    }


    @PutMapping("/{poId}/receive")
    public PoResponse receivePurchaseOrder(@PathVariable UUID poId, @Valid @RequestBody ReceivePoRequest payload) {
        return this.poService.receivePurchaseOrder(poId, payload);
    }

    @PostMapping("/{poId}/items")
    public ResponseEntity<PoResponse> addItem(@PathVariable UUID poId, @Valid @RequestBody PoItemRequest payload) {
        var item = poService.addPoItem(poId, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{poId}/items/{itemId}")
    public ResponseEntity<PoResponse> updateItem(@PathVariable UUID poId, @PathVariable UUID itemId, @Valid @RequestBody PoItemRequest payload) {
        var item = poService.updateItem(poId, itemId, payload);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{poId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable UUID poId, @PathVariable UUID itemId) {
        poService.removeItem(poId, itemId);
        return ResponseEntity.noContent().build();
    }
}