package com.andreiverse.http.common.service;

import com.andreiverse.http.common.entity.UserEntity;

import io.micronaut.context.annotation.Secondary;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@Secondary
public class DefaultUserInitializer implements UserInitializer {

    @Override
    public UserEntity initialize(UserEntity userEntity, UserService userService) {
        log.info("Ran default initializer...");
        return userEntity;
    }
}