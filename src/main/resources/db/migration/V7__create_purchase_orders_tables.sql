-- 1. Create enum for purchase order lifecycle status
CREATE TYPE PURCHASE_ORDER_STATUS AS ENUM ('PENDING', 'APPROVED', 'RECEIVED', 'CANCELLED');

-- 2. Create purchase orders header table (extends BaseEntity concept: id, is_deleted, created_at, updated_at)
CREATE TABLE purchase_orders
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    supplier_id UUID                     NOT NULL,
    status      PURCHASE_ORDER_STATUS    NOT NULL DEFAULT 'PENDING',
    reference   VARCHAR(255)                      DEFAULT NULL, -- e.g. PO-2026-001
    notes       TEXT,
    is_deleted  BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_po_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES suppliers (id)
            ON DELETE RESTRICT                                  -- Don't let a supplier be deleted if POs exist
);

-- 3. Create purchase order items table (line items)
CREATE TABLE purchase_order_items
(
    id                UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    purchase_order_id UUID                     NOT NULL,
    product_id        UUID                     NOT NULL,
    quantity_ordered  INT                      NOT NULL CHECK (quantity_ordered > 0),
    quantity_received INT                      NOT NULL DEFAULT 0 CHECK (quantity_received >= 0),
    unit_cost         NUMERIC(12, 2)           NOT NULL CHECK (unit_cost >= 0),
    is_deleted        BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_poi_purchase_order
        FOREIGN KEY (purchase_order_id)
            REFERENCES purchase_orders (id)
            ON DELETE CASCADE, -- If PO is deleted, drop its line items

    CONSTRAINT fk_poi_product
        FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE RESTRICT
);

-- Indexes for performance
CREATE INDEX idx_purchase_orders_supplier_id ON purchase_orders (supplier_id);
CREATE INDEX idx_purchase_orders_status ON purchase_orders (status);
CREATE INDEX idx_po_items_po_id ON purchase_order_items (purchase_order_id);
CREATE INDEX idx_po_items_product_id ON purchase_order_items (product_id);