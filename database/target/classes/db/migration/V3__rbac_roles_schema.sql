CREATE TABLE roles (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE (name)
);

CREATE TABLE roles_permissions (
    role_id UUID NOT NULL,
    permission_name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_roles_permissions PRIMARY KEY (role_id, permission_name),
    CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_name) REFERENCES permissions (name)
);

CREATE TABLE users_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES roles (id)
);
