package com.mwibutsa.stockflow.supplier;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.supplier.dto.SupplierRequest;
import com.mwibutsa.stockflow.supplier.dto.SupplierResponse;
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
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public PaginatedResponse<SupplierResponse> getSuppliers(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "1") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String direction
    ) {

        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.valueOf("ASC"));
        Pageable pagination = PageRequest.of(Math.max(page, 1) - 1, size, Sort.by(sortDir, sortBy));

        return supplierService.getAllSuppliers(search, pagination);
    }


    @PostMapping
    public ResponseEntity<SupplierResponse> addNewSupplier(
            @Valid @RequestBody SupplierRequest payload,
            UriComponentsBuilder uriBuilder
    ) {
        var supplier = supplierService.addSupplier(payload);
        var uri = uriBuilder.path("/suppliers/{id}").buildAndExpand(supplier.getId()).toUri();
        return ResponseEntity.created(uri).body(supplier);
    }

    @GetMapping("/{supplierId}")
    public SupplierResponse getSupplier(@PathVariable UUID supplierId) {
        return supplierService.getSupplier(supplierId);
    }

    @PutMapping("/{supplierId}")
    public SupplierResponse updateSupplier(@PathVariable UUID supplierId, @RequestBody SupplierRequest payload) {
        return supplierService.updateSupplier(supplierId, payload);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID supplierId) {
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
