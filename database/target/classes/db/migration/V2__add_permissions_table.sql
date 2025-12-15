CREATE TABLE permissions (
    name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_permissions PRIMARY KEY (name),
    CONSTRAINT uq_permissions_name UNIQUE (name)
);
