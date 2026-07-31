ALTER TABLE products
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE;

CREATE INDEX idx_products_is_deleted ON products (is_deleted);