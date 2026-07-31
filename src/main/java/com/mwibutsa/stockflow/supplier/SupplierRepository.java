package com.mwibutsa.stockflow.supplier;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID>,
        JpaSpecificationExecutor<Supplier> {

    @Override
    @NonNull Page<Supplier> findAll(@NonNull Specification<Supplier> spec, @NonNull Pageable pageable);

    Optional<Supplier> findByEmail(String email);
}