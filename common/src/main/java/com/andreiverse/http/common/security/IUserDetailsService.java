package com.andreiverse.http.common.security;

import com.andreiverse.http.common.entity.BaseUserDetailsEntity;
import com.andreiverse.http.common.entity.UserEntity;

public interface IUserDetailsService<T extends BaseUserDetailsEntity> {
    T createAndSaveUserDetails(UserEntity userEntity);
}
