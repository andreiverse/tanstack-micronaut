package com.andreiverse.demo.security;

import com.andreiverse.http.common.security.authorization.PermissionSeeder;
import com.andreiverse.http.common.security.authorization.RoleSeeder;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class PermissionStartup implements ApplicationEventListener<ServerStartupEvent> {

    private final PermissionSeeder permissionSeeder;
    private final RoleSeeder roleSeeder;
    private final com.andreiverse.http.common.CommonServerConfig serverConfig;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        permissionSeeder.seed(serverConfig.getPermissions());
        roleSeeder.seed(serverConfig.getRoles());
    }
}
