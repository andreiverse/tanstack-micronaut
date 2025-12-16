package com.andreiverse.demo.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum AppRole implements com.andreiverse.http.common.security.authorization.Role {
        ADMIN(
                        AppPermission.ARTICLE_READ,
                        AppPermission.ARTICLE_WRITE,
                        AppPermission.USER_READ,
                        AppPermission.USER_WRITE),
        MEMBER(
                        AppPermission.ARTICLE_READ);

        private final List<String> permissions;

        AppRole(AppPermission... permissions) {
                this.permissions = Arrays.stream(permissions)
                                .map(AppPermission::getName)
                                .collect(Collectors.toList());
        }

        @Override
        public String getName() {
                return name();
        }
}
