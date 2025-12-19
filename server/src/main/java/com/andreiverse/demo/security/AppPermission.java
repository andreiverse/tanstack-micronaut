package com.andreiverse.demo.security;

import com.andreiverse.http.common.security.authorization.Permission;

public enum AppPermission implements Permission {
    TEST_PERMISSION("TEST_PERMISSION");

    private final String name;

    AppPermission(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
