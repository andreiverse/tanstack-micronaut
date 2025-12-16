package com.andreiverse.http.common.security.authorization;

import java.util.List;
import java.util.Optional;

import com.andreiverse.http.common.CommonServerConfig.RoleDefinition;
import com.andreiverse.http.common.entity.PermissionEntity;
import com.andreiverse.http.common.entity.RoleEntity;
import com.andreiverse.http.common.repository.PermissionRepository;
import com.andreiverse.http.common.repository.RoleRepository;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    public void seed(List<RoleDefinition> definitions) {
        log.info("Seeding roles...");
        for (RoleDefinition def : definitions) {
            if (roleRepository.findByName(def.name()).isEmpty()) {
                log.info("Creating role: {}", def.name());
                List<PermissionEntity> permissions = def.permissions().stream()
                        .map(permissionRepository::findByName)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();

                RoleEntity role = RoleEntity.builder()
                        .name(def.name())
                        .permissions(permissions)
                        .build();

                roleRepository.save(role);
            }
        }
    }
}
