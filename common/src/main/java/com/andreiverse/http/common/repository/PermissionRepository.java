package com.andreiverse.http.common.repository;

import java.util.Optional;

import com.andreiverse.http.common.entity.PermissionEntity;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, String> {
    void deleteByName(String name);

    Optional<PermissionEntity> findByName(String name);
}
