package com.andreiverse.http.common.repository;

import java.util.Optional;
import java.util.UUID;

import com.andreiverse.http.common.entity.UserEntity;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(UUID id);

}
