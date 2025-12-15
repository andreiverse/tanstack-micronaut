package com.andreiverse.http.common;

import java.util.List;

import com.andreiverse.http.common.security.permission.Permission;

public interface CommonServerConfig {
    List<Permission> getPermissions();
}
