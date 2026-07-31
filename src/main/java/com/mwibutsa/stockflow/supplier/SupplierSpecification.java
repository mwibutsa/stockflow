package com.mwibutsa.stockflow.supplier;

import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class SupplierSpecification {
    public static Specification<Supplier> searchProducts(String keyword) {
        return (root, query, criteriaBuilder) -> {

            if (keyword == null || keyword.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            String pattern = "%" + keyword.toLowerCase(Locale.ROOT) + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("contactPerson")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), pattern)
            );
        };
    }
}

