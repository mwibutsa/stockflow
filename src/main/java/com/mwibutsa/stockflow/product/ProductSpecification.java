package com.mwibutsa.stockflow.product;


import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class ProductSpecification {
    public static Specification<Product> searchProducts(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            String pattern = "%" + keyword.toLowerCase(Locale.ROOT) + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("sku")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern)
            );
        };
    }
}
