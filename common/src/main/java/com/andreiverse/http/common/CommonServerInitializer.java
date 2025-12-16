package com.andreiverse.http.common;

import com.andreiverse.http.common.security.authorization.PermissionSeeder;

import io.micronaut.context.event.StartupEvent;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CommonServerInitializer implements io.micronaut.context.event.ApplicationEventListener<StartupEvent> {
    private final PermissionSeeder permissionSeeder;
    private final CommonServerConfig commonServerConfig;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        permissionSeeder.seed(commonServerConfig.getPermissions());
    }
}
