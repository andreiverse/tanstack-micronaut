package com.andreiverse.http.common;

import com.andreiverse.http.common.security.authorization.Permission;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * Test implementation of CommonServerConfig for use in tests.
 * This bean is active only in the test environment.
 */
@Singleton
@Requires(env = "test")
public class TestCommonServerConfig implements CommonServerConfig {

    private final List<Permission> permissions;
    private final List<RoleDefinition> roles;

    /**
     * Default constructor with predefined test data.
     */
    public TestCommonServerConfig() {
        this.permissions = List.of(
            new TestPermission("READ"),
            new TestPermission("WRITE"),
            new TestPermission("DELETE"),
            new TestPermission("ADMIN")
        );

        this.roles = List.of(
            new RoleDefinition("USER", List.of("READ")),
            new RoleDefinition("EDITOR", List.of("READ", "WRITE")),
            new RoleDefinition("ADMIN", List.of("READ", "WRITE", "DELETE", "ADMIN"))
        );
    }

    /**
     * Constructor for custom test data.
     */
    public TestCommonServerConfig(List<Permission> permissions, List<RoleDefinition> roles) {
        this.permissions = permissions;
        this.roles = roles;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public List<RoleDefinition> getRoles() {
        return roles;
    }

    /**
     * Simple test implementation of Permission.
     */
    public static class TestPermission implements Permission {
        private final String name;

        public TestPermission(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
