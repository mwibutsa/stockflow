CREATE TYPE STOCK_TRANSACTION_TYPE AS ENUM ('STOCK_IN', 'STOCK_OUT', 'ADJUSTMENT');

CREATE TABLE stock_transactions
(
    id             UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    product_id     UUID                   NOT NULL,
    type           STOCK_TRANSACTION_TYPE NOT NULL,
    quantity       INT                    NOT NULL,
    previous_stock INT                    NOT NULL,
    new_stock      INT                    NOT NULL,
    reference      VARCHAR(255), -- e.g., "PO-1002" or "Invoice #492"
    notes          TEXT,
    created_at     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE INDEX idx_stock_transactions_product_id ON stock_transactions (product_id);