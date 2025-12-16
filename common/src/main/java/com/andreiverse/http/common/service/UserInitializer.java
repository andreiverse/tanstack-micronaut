package com.andreiverse.http.common.service;

import com.andreiverse.http.common.entity.UserEntity;

public interface UserInitializer {
    public UserEntity initialize(UserEntity userEntity, UserService userService);
}
