package com.andreiverse.demo.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.andreiverse.http.common.security.authorization.CommonPermission;
import com.andreiverse.http.common.security.authorization.Permission;
import com.andreiverse.http.common.security.authorization.Role;

import lombok.Getter;

@Getter
public enum AppRole implements Role {
        ADMIN(
                        AppPermission.TEST_PERMISSION,
                        CommonPermission.CREATE_USERS,
                        CommonPermission.DELETE_ALL_USERS,
                        CommonPermission.EDIT_ALL_USERS,
                        CommonPermission.VIEW_ALL_USERS),
        MEMBER(
                        AppPermission.TEST_PERMISSION);

        private final List<String> permissions;

        AppRole(Permission... permissions) {
                this.permissions = Arrays.stream(permissions)
                                .map(Permission::getName)
                                .collect(Collectors.toList());
        }

        @Override
        public String getName() {
                return name();
        }
}
