package com.andreiverse.http.common.repository;

import java.util.Optional;
import java.util.UUID;

import com.andreiverse.http.common.entity.RoleEntity;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(String name);
}
