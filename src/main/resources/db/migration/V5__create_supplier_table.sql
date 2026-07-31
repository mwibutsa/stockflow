CREATE TABLE suppliers
(
    id             UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name           VARCHAR(255)             NOT NULL,
    contact_person VARCHAR(255),
    email          VARCHAR(255) UNIQUE, -- Enforces unique corporate emails
    phone          VARCHAR(50),
    address        TEXT,
    is_active      BOOLEAN                  NOT NULL DEFAULT TRUE,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP WITH TIME ZONE
);

CREATE INDEX idx_suppliers_name ON suppliers (name);
CREATE INDEX idx_suppliers_email ON suppliers (email);