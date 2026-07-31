package com.mwibutsa.stockflow.product;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    @Query("select (count(p) > 0) from Product p where p.sku = :sku")
    boolean existsBySku(@Param("sku") String sku);

    @Query("select (count(p) > 0) from Product p where p.barcode = :barcode")
    boolean existsByBarcode(@Param("barcode") String barcode);

    @EntityGraph(attributePaths = "category")
    @Override
    @NonNull Page<Product> findAll(@NonNull Specification<Product> spec, @NonNull Pageable pageable);

}