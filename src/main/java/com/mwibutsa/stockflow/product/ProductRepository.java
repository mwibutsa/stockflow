package com.mwibutsa.stockflow.product;

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

    @Query("select p from Product p")
    @EntityGraph(attributePaths = "category")
    Page<Product> findAllWithCategories(@Param("spec") Specification<Product> spec, Pageable pageable);
}