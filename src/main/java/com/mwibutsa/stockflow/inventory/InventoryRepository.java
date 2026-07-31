package com.mwibutsa.stockflow.inventory;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface InventoryRepository extends
        JpaRepository<InventoryTransaction, UUID>,
        JpaSpecificationExecutor<InventoryTransaction> {

    @EntityGraph(attributePaths = "product")
    @NonNull Page<InventoryTransaction> findAll(@NonNull Specification<InventoryTransaction> spec, @NonNull Pageable pageable);

}