package com.andreiverse.http.common.security.authentication;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.reactivestreams.Publisher;

import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class AuthenticationProviderUserPassword<B> implements HttpRequestReactiveAuthenticationProvider<B> {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(
            @Nullable HttpRequest<B> httpRequest,
            @NonNull AuthenticationRequest<String, String> authenticationRequest) {
        log.info("Authentication request: {}", authenticationRequest.getIdentity());
        return Flux.create(emitter -> {
            Optional<UserEntity> userEntityOptional = userService.findByEmail(authenticationRequest.getIdentity());
            if (userEntityOptional.isPresent()) {
                UserEntity userEntity = userEntityOptional.get();
                if (passwordEncoder.matches(authenticationRequest.getSecret(), userEntity.getHashedPassword())) {
                    // Flatten Roles and Permissions into a single list of strings
                    java.util.List<String> authorities = new java.util.ArrayList<>();

                    if (userEntity.getRoles() != null) {
                        for (com.andreiverse.http.common.entity.RoleEntity role : userEntity.getRoles()) {
                            // Add Role Name (e.g. "ROLE_ADMIN")
                            authorities.add("ROLE_" + role.getName()); // Standard convention, but customizable

                            // Add Permissions (e.g. "ARTICLE_WRITE")
                            if (role.getPermissions() != null) {
                                for (com.andreiverse.http.common.entity.PermissionEntity perm : role.getPermissions()) {
                                    authorities.add(perm.getName());
                                }
                            }
                        }
                    }

                    emitter.next(
                            AuthenticationResponse.success((String) authenticationRequest.getIdentity(), authorities));
                    emitter.complete();
                } else {
                    emitter.error(AuthenticationResponse.exception());
                }
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
