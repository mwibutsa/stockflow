package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.product.Product;
import com.mwibutsa.stockflow.supplier.Supplier;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;
import java.util.UUID;

public class PoSpecification {
    public static Specification<Po> searchPurchaseOrders(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            query.distinct(true);

            String pattern = "%" + keyword.toLowerCase(Locale.ROOT) + "%";
            Join<Po, Supplier> supplierJoin = root.join("supplier", JoinType.INNER);

            Join<Po, PoItem> itemsJoin = root.join("items", JoinType.LEFT);

            Join<PoItem, Product> productJoin = itemsJoin.join("product", JoinType.LEFT);

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("notes")), pattern),
                    // supplier
                    criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("email")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("address")), pattern),

                    // product via items
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("sku")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("description")), pattern)
            );
        };
    }

    public static Specification<Po> filterByStatus(PoStatus status) {

        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Po> filterBySupplier(UUID supplierId) {

        return (root, query, criteriaBuilder) -> {
            if (supplierId == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("supplier").get("id"), supplierId);
        };
    }
}

