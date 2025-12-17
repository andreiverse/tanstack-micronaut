package com.andreiverse.demo.service;

import com.andreiverse.demo.entity.UserDetailsEntity;
import com.andreiverse.demo.repository.AppUserDetailsRepository;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.security.IUserDetailsService;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Server implementation of IUserDetailsService.
 * Creates and manages server-specific user details.
 */
@Slf4j
@Singleton
@RequiredArgsConstructor
public class AppUserDetailsService implements IUserDetailsService<UserDetailsEntity> {
    private final AppUserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsEntity createAndSaveUserDetails(UserEntity userEntity) {
        log.info("Creating user details for user: {}", userEntity.getEmail());

        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setUser(userEntity);
        userDetails.setDescription(""); // Default empty description

        return userDetailsRepository.save(userDetails);
    }
}
