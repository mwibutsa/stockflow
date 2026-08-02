-- 1. Users table (Auditable + Soft Delete friendly)
CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name   VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    is_active  BOOLEAN      NOT NULL    DEFAULT TRUE, -- Often used instead of soft-delete for users to preserve history
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. Roles table (Static lookup)exit
CREATE TABLE roles
(
    id   UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- 3. Permissions table (Static lookup)
CREATE TABLE permissions
(
    id   UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- 4. User-Roles join table (No audit/soft delete needed)
CREATE TABLE user_roles
(
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id UUID NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- 5. Role-Permissions join table (No audit/soft delete needed)
CREATE TABLE role_permissions
(
    role_id       UUID NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    permission_id UUID NOT NULL REFERENCES permissions (id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, permission_id)
);

-- 6. Indexes for fast authentication lookups
CREATE INDEX idx_users_email ON users (email);