package com.andreiverse.http.common;

import java.util.List;

import com.andreiverse.http.common.security.authorization.Permission;

public interface CommonServerConfig {
    List<Permission> getPermissions();

    List<RoleDefinition> getRoles();

    record RoleDefinition(String name, List<String> permissions) {
    }
}
