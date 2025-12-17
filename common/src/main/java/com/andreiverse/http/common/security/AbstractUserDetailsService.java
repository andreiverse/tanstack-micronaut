package com.andreiverse.http.common.security;

import com.andreiverse.http.common.entity.BaseUserDetailsEntity;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.repository.UserDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
public abstract class AbstractUserDetailsService<T extends BaseUserDetailsEntity> {

    protected final UserDetailsRepository<T> repository;
    protected final EntityManager entityManager;

    public AbstractUserDetailsService(UserDetailsRepository<T> repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public abstract T createAndSaveUserDetails(UserEntity userEntity);

    public T getUserDetails(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("User entity cannot be null");
        }

        return this.repository.findByUserId(userEntity.getId())
                .orElseGet(() -> this.createAndSaveUserDetails(userEntity));
    }

}
