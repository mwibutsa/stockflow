CREATE TABLE products
(
    id              UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    name            VARCHAR(255)   NOT NULL,
    sku             VARCHAR(100)   NOT NULL UNIQUE,
    barcode         VARCHAR(100) UNIQUE,
    unit            VARCHAR(100)   NOT NULL  DEFAULT 'pcs',
    description     TEXT,
    price           NUMERIC(12, 2) NOT NULL CHECK (price >= 0),
    cost_price      NUMERIC(12, 2) NOT NULL CHECK (cost_price >= 0),
    stock_quantity  INT            NOT NULL  DEFAULT 0 CHECK (stock_quantity >= 0),
    min_stock_level INT            NOT NULL  DEFAULT 0 CHECK (min_stock_level >= 0),
    category_id     UUID           NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Foreign Key link to your existing categories table
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE RESTRICT -- Prevents deleting a category if products are still attached
);

-- Index for lightning-fast lookups by SKU and Category
CREATE INDEX idx_products_sku ON products (sku);
CREATE INDEX idx_products_category_id ON products (category_id);