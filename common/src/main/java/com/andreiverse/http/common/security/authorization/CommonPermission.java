package com.andreiverse.http.common.security.authorization;

/**
 * Common permissions that can be used across different applications.
 * These permissions represent standard operations that most applications need.
 */
public enum CommonPermission implements Permission {
    // User Management Permissions (Admin-level)
    VIEW_ALL_USERS("VIEW_ALL_USERS"),
    EDIT_ALL_USERS("EDIT_ALL_USERS"),
    CREATE_USERS("CREATE_USERS"),
    DELETE_ALL_USERS("DELETE_ALL_USERS");

    private final String name;

    CommonPermission(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
