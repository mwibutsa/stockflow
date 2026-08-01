package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.entity.BaseEntity;
import com.mwibutsa.stockflow.common.exception.PurchaseOrderItemNotFoundException;
import com.mwibutsa.stockflow.po.dto.PoItemRequest;
import com.mwibutsa.stockflow.supplier.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase_orders")
@AllArgsConstructor
public class Po extends BaseEntity {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "purchase_order_status")
    private PoStatus status = PoStatus.PENDING;

    @Column(name = "reference")
    private String reference;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<PoItem> items = new LinkedHashSet<>();

    public void addItem(PoItem item) {
        this.items.add(item);
        item.setPurchaseOrder(this);
    }

    public void removeItem(UUID itemId) {
        var item = getItem(itemId);
        this.items.remove(item);
        item.setPurchaseOrder(null);
    }

    public PoItem getItem(UUID itemId) {
        return this.items.stream().filter(poItem -> poItem.getId().equals(itemId)).findFirst()
                .orElseThrow(PurchaseOrderItemNotFoundException::new);
    }

    public PoItem updateItem(UUID itemId, PoItemRequest payload) {
        var item = getItem(itemId);
        item.setQuantityOrdered(payload.getQuantityOrdered());
        item.setUnitCost(payload.getUnitCost());
        return item;
    }

    public PoItem receiveQuantity(UUID itemId, Integer quantity) {
        var item = getItem(itemId);
        item.setQuantityReceived(quantity);
        return item;
    }

    public boolean existsByProductId(UUID productId) {
        var exists = items.stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElse(null);
        return exists != null;
    }
}