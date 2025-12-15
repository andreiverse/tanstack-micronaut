package com.andreiverse.demo;

import java.util.List;

import com.andreiverse.demo.security.AppPermission;
import com.andreiverse.http.common.CommonServerConfig;
import com.andreiverse.http.common.security.permission.Permission;

import io.micronaut.context.annotation.Import;
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
    }

}
