package com.andreiverse.demo.service;

import com.andreiverse.demo.entity.UserDetailsEntity;
import com.andreiverse.demo.repository.AppUserDetailsRepository;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.security.AbstractUserDetailsService;

import jakarta.persistence.EntityManager;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Server implementation of IUserDetailsService.
 * Creates and manages server-specific user details.
 */
@Slf4j
@Singleton
@Transactional
public class AppUserDetailsService extends AbstractUserDetailsService<UserDetailsEntity> {

    public AppUserDetailsService(AppUserDetailsRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    public UserDetailsEntity createAndSaveUserDetails(UserEntity userEntity) {
        log.info("Initializing user details for: {}", userEntity.getEmail());

        if (!entityManager.contains(userEntity)) {
            userEntity = entityManager.merge(userEntity);
        }

        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setUserId(userEntity.getId());
        userDetails.setUser(userEntity);
        userDetails.setDescription(""); // Default empty description

        return this.repository.save(userDetails);
    }
}
