package com.andreiverse.demo.security;

import com.andreiverse.http.common.security.permission.Permission;

public enum AppPermission implements Permission {
    TEST;

    @Override
    public String getName() {
        return name();
    }
}
