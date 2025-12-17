package com.andreiverse.demo.repository;

import com.andreiverse.demo.entity.UserDetailsEntity;
import com.andreiverse.http.common.repository.UserDetailsRepository;

import io.micronaut.data.annotation.Repository;

@Repository
public interface AppUserDetailsRepository extends UserDetailsRepository<UserDetailsEntity> {
    // Inherits all CRUD operations and findByUserId from parent
    // Add any server-specific query methods here if needed
}
