package com.andreiverse.demo;

import java.util.List;

import com.andreiverse.demo.security.AppPermission;
import com.andreiverse.demo.security.AppRole;
import com.andreiverse.http.common.CommonServerConfig;
import com.andreiverse.http.common.security.authorization.Permission;

import io.micronaut.openapi.annotation.OpenAPISecurity;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(info = @Info(title = "server", version = "1.0.0"))

@OpenAPISecurity
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @jakarta.inject.Singleton
    public static class ServerConfig implements CommonServerConfig {
        @Override
        public List<Permission> getPermissions() {
            return List.of(AppPermission.values());
        }

        @Override
        public List<RoleDefinition> getRoles() {
            return java.util.Arrays.stream(AppRole.values())
                    .map(role -> new RoleDefinition(role.name(), role.getPermissions()))
                    .toList();
        }
    }

}
