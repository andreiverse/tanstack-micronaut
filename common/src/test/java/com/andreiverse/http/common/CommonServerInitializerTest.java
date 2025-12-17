package com.andreiverse.http.common;

import com.andreiverse.http.common.CommonServerConfig.RoleDefinition;
import com.andreiverse.http.common.repository.PermissionRepository;
import com.andreiverse.http.common.repository.RoleRepository;
import com.andreiverse.http.common.security.authorization.Permission;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class CommonServerInitializerTest {

    @Inject
    PermissionRepository permissionRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    CommonServerConfig commonServerConfig;

    @Test
    void testPermissionsAreSeeded() {
        long totalPermissions = commonServerConfig.getPermissions().size();
        long totalRoles = commonServerConfig.getRoles().size();

        assertEquals(totalPermissions, permissionRepository.count());
        assertEquals(totalRoles, roleRepository.count());

        for (Permission permission : commonServerConfig.getPermissions()) {
            assertTrue(permissionRepository.findByName(permission.getName()).isPresent());
        }

        for (RoleDefinition role : commonServerConfig.getRoles()) {
            assertTrue(roleRepository.findByName(role.name()).isPresent());

            for (String permissionName : role.permissions()) {
                assertTrue(permissionRepository.findByName(permissionName).isPresent());
                assertTrue(roleRepository.findByName(role.name()).get().getPermissions().stream()
                        .anyMatch(p -> p.getName().equals(permissionName)));
            }
        }
    }
}
