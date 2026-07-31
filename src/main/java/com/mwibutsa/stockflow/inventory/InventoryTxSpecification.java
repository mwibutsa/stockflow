package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.product.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;
import java.util.UUID;

public class InventoryTxSpecification {
    public static Specification<InventoryTransaction> searchTransactions(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            String pattern = "%" + keyword.toLowerCase(Locale.ROOT) + "%";

            Join<InventoryTransaction, Product> productJoin = root.join("product", JoinType.INNER);
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("notes")), pattern),

                    // product join
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("description")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("sku")), pattern)
            );
        };
    }

    public static Specification<InventoryTransaction> hasProductId(UUID productId) {
        return (root, query, criterialBuilder) -> {
            if (productId == null) {
                return criterialBuilder.isTrue(criterialBuilder.literal(true));
            }
            return criterialBuilder.equal(root.get("product").get("id"), productId);
        };
    }
}
