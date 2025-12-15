package com.andreiverse.http.common.security.permission;

import java.util.List;

import com.andreiverse.http.common.entity.PermissionEntity;
import com.andreiverse.http.common.repository.PermissionRepository;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class PermissionSeeder {
    private final PermissionRepository permissionRepository;

    public void seed(List<Permission> permissions) {
        log.info("Seeding permissions: {}", permissions.stream().map(Permission::getName).toList());
        permissions.stream()
                .filter(permission -> permissionRepository.findByName(permission.getName()).isEmpty())
                .map(PermissionEntity::from)
                .forEach(permissionRepository::save);
    }

}
