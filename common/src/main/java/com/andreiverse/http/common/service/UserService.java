package com.andreiverse.http.common.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.entity.RoleEntity;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.repository.RoleRepository;
import com.andreiverse.http.common.repository.UserRepository;
import com.andreiverse.http.common.security.AbstractUserDetailsService;
import com.andreiverse.http.common.security.authentication.PasswordEncoder;
import com.andreiverse.http.common.security.authorization.Role;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserInitializer userInitializer;

    private final PasswordEncoder passwordEncoder;

    private final Optional<AbstractUserDetailsService<?>> userDetailsService;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity register(UserRegistrationRequest userRegistrationRequest) {
        return register(userRegistrationRequest, List.of());
    }

    public UserEntity setRoles(UUID userId, List<? extends Role> roles) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userEntity.setRoles(roles.stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList());

        return userRepository.save(userEntity);
    }

    public UserEntity register(UserRegistrationRequest userRegistrationRequest,
            List<? extends Role> roles) {
        if (findByEmail(userRegistrationRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userRegistrationRequest.getEmail());
        userEntity.setHashedPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        if (roles != null && !roles.isEmpty()) {
            List<RoleEntity> foundRoles = roles.stream()
                    .map(role -> roleRepository.findByName(role.getName()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            userEntity.setRoles(foundRoles);
        } else {
            userEntity.setRoles(List.of());
        }

        UserEntity uninitializedUser = userRepository.save(userEntity);
        UserEntity initializedUser = userInitializer.initialize(uninitializedUser, this);

        if (userDetailsService.isPresent()) {
            userDetailsService.get().createAndSaveUserDetails(initializedUser);
        }

        return initializedUser;
    }
}
