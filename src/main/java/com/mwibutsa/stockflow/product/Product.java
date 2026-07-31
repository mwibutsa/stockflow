package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.category.Category;
import com.mwibutsa.stockflow.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "sku")
    private String sku;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @ColumnDefault("0")
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @ColumnDefault("0")
    @Column(name = "min_stock_level")
    private Integer minStockLevel;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "unit")
    private String unit;
}