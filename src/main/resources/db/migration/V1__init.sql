-- Enable UUID extension in PostgreSQL if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create categories table using UUID
CREATE TABLE categories
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);