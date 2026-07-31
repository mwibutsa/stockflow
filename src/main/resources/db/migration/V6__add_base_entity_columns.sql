-- 1. Add missing soft delete and updated_at columns to categories
ALTER TABLE categories
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP;

-- 2. Add missing soft delete column to suppliers (and ensure updated_at exists)
ALTER TABLE suppliers
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE;

-- 3. Add missing soft delete and updated_at columns to stock_transactions
-- (Note: Audit fields on log tables are great for tracking lifecycle consistency)
ALTER TABLE stock_transactions
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP;

-- Helpful indexes for filtering active vs deleted records across your entities
CREATE INDEX idx_categories_is_deleted ON categories (is_deleted);
CREATE INDEX idx_suppliers_is_deleted ON suppliers (is_deleted);
CREATE INDEX idx_stock_transactions_is_deleted ON stock_transactions (is_deleted);