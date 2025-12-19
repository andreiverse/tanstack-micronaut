package com.andreiverse.http.common;

import java.util.List;

import com.andreiverse.http.common.security.authorization.CommonPermission;
import com.andreiverse.http.common.security.authorization.PermissionSeeder;
import com.andreiverse.http.common.security.authorization.RoleSeeder;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CommonServerInitializer implements ApplicationEventListener<StartupEvent> {
    private final PermissionSeeder permissionSeeder;
    private final RoleSeeder roleSeeder;
    private final CommonServerConfig commonServerConfig;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        permissionSeeder.seed(List.of(CommonPermission.values()));
        permissionSeeder.seed(commonServerConfig.getPermissions());
        roleSeeder.seed(commonServerConfig.getRoles());
    }
}
