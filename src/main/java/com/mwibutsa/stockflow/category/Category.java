package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.common.entity.BaseEntity;
import com.mwibutsa.stockflow.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}