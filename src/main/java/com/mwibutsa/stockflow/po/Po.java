package com.mwibutsa.stockflow.po;

import com.mwibutsa.stockflow.common.entity.BaseEntity;
import com.mwibutsa.stockflow.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders")
public class Po extends BaseEntity {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @ColumnDefault("'PENDING'")
    @Column(name = "status", columnDefinition = "purchase_order_status")
    private PoStatus status;

    @Column(name = "reference")
    private String reference;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "purchaseOrder")
    private Set<PoItem> purchaseOrderItems = new LinkedHashSet<>();
}