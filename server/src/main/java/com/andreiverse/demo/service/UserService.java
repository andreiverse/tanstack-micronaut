package com.andreiverse.demo.service;

import java.util.Optional;
import java.util.UUID;

import com.andreiverse.demo.domain.UserRegistrationRequest;
import com.andreiverse.demo.entity.UserEntity;
import com.andreiverse.demo.repository.UserRepository;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity register(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userRegistrationRequest.getEmail());
        userEntity.setHashedPassword(userRegistrationRequest.getPassword());

        return userRepository.save(userEntity);
    }
}
