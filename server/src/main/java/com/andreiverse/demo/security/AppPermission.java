package com.andreiverse.demo.security;

import com.andreiverse.http.common.security.authorization.Permission;

public enum AppPermission implements Permission {
    ARTICLE_READ("ARTICLE_READ"),
    ARTICLE_WRITE("ARTICLE_WRITE"),
    USER_READ("USER_READ"),
    USER_WRITE("USER_WRITE");

    private final String name;

    AppPermission(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
