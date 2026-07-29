-- Enable UUID extension in PostgreSQL if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create categories table using UUID
CREATE TABLE categories (
                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            name VARCHAR(255) NOT NULL UNIQUE,
                            description TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create products table using UUID and structured human-readable SKU
-- Create products table with RESTRICT on category deletion
CREATE TABLE products (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          sku VARCHAR(100) NOT NULL UNIQUE,
                          barcode VARCHAR(100) UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          buying_price NUMERIC(10, 2) NOT NULL,
                          selling_price NUMERIC(10, 2) NOT NULL,
                          stock_quantity INTEGER NOT NULL DEFAULT 0,
                          min_stock_level INTEGER NOT NULL DEFAULT 5,
                          category_id UUID,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);