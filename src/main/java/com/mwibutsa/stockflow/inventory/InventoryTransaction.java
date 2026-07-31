package com.mwibutsa.stockflow.inventory;

import com.mwibutsa.stockflow.common.entity.BaseEntity;
import com.mwibutsa.stockflow.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "stock_transactions")
public class InventoryTransaction extends BaseEntity {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type", columnDefinition = "stock_transaction_type")
    private StockTransactionType type;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "previous_stock")
    private Integer previousStock; //

    @Column(name = "new_stock")
    private Integer newStock;

    @Column(name = "reference")
    private String reference;

    @Column(name = "notes")
    private String notes;
}