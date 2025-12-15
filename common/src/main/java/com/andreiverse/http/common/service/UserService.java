package com.andreiverse.http.common.service;

import java.util.Optional;
import java.util.UUID;

import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.repository.UserRepository;
import com.andreiverse.http.common.security.PasswordEncoder;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity register(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userRegistrationRequest.getEmail());
        userEntity.setHashedPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));

        return userRepository.save(userEntity);
    }
}
