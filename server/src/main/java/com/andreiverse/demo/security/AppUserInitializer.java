package com.andreiverse.demo.security;

import java.util.List;

import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserInitializer;
import com.andreiverse.http.common.service.UserService;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class AppUserInitializer implements UserInitializer {

    @Override
    public UserEntity initialize(UserEntity userEntity, UserService userService) {
        log.info("Initializing user: {}", userEntity.getEmail());
        return userService.setRoles(userEntity.getId(), List.of(AppRole.MEMBER));
    }
}
