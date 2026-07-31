package com.mwibutsa.stockflow.supplier;

import com.mwibutsa.stockflow.common.dto.PaginatedResponse;
import com.mwibutsa.stockflow.common.exception.ConflictException;
import com.mwibutsa.stockflow.common.exception.SupplierNotFoundException;
import com.mwibutsa.stockflow.supplier.dto.SupplierRequest;
import com.mwibutsa.stockflow.supplier.dto.SupplierResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierMapper supplierMapper, SupplierRepository supplierRepository) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }

    public PaginatedResponse<SupplierResponse> getAllSuppliers(String search, Pageable pagination) {
        var spec = SupplierSpecification.searchProducts(search);
        return supplierMapper.toPageResponse(supplierRepository.findAll(spec, pagination));

    }

    public SupplierResponse addSupplier(SupplierRequest payload) {
        // check unique email.
        ensureSupplierEmailAvailability(payload.getEmail());

        var supplier = supplierMapper.toEntity(payload);

        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    private void ensureSupplierEmailAvailability(String email) {
        var existingSupplier = supplierRepository.findByEmail(email)
                .orElse(null);

        if (existingSupplier != null) {
            throw new ConflictException("Email is already being used", "email");
        }
    }

    public SupplierResponse getSupplier(UUID supplierId) {
        var supplier = supplierRepository.findById(supplierId).orElseThrow(SupplierNotFoundException::new);
        return supplierMapper.toDto(supplier);
    }

    public SupplierResponse updateSupplier(UUID supplierId, SupplierRequest payload) {
        var supplier = supplierRepository.findById(supplierId).orElseThrow(SupplierNotFoundException::new);

        if (!Objects.equals(supplier.getEmail(), payload.getEmail())) {
            ensureSupplierEmailAvailability(payload.getEmail());
        }

        supplierMapper.update(payload, supplier);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }
}
