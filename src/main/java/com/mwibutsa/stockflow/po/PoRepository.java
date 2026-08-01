package com.mwibutsa.stockflow.po;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PoRepository extends JpaRepository<Po, UUID>, JpaSpecificationExecutor<Po> {
    @EntityGraph(attributePaths = "supplier")
    @Override
    @NonNull Page<Po> findAll(@NonNull Specification<Po> spec, @NonNull Pageable pageable);

    @Query("select p from Po p where p.id = :poId")
    @EntityGraph(attributePaths = {"supplier", "items", "items.product"})
    Optional<Po> findWithItems(@Param("poId") UUID poId);

    @Query("""
            select (count(p) > 0) from Po p inner join p.items items
            where p.id = :id and items.product.id = :itemsProductId""")
    boolean existsByIdAndItemsProductId(@Param("id") UUID id, @Param("itemsProductId") UUID itemsProductId);
}