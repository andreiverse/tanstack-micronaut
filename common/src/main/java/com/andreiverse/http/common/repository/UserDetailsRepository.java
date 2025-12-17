package com.andreiverse.http.common.repository;

import java.util.Optional;
import java.util.UUID;

import com.andreiverse.http.common.entity.BaseUserDetailsEntity;

import io.micronaut.data.repository.CrudRepository;

/**
 * Base repository interface for user details entities.
 * Concrete implementations should extend this interface and specify the entity
 * type.
 */
public interface UserDetailsRepository<T extends BaseUserDetailsEntity> extends CrudRepository<T, UUID> {

    /**
     * Find user details by user ID.
     * Note: Since userId is the primary key (via @MapsId), this is equivalent to
     * findById.
     */
    Optional<T> findByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
