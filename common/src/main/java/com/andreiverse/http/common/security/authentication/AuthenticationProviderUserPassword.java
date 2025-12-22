package com.andreiverse.http.common.security.authentication;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;

import com.andreiverse.http.common.entity.PermissionEntity;
import com.andreiverse.http.common.entity.RoleEntity;
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
        log.info("Received authentication request for: {}", authenticationRequest.getIdentity());

        return Flux.create(emitter -> {
            resolveAuthentication(authenticationRequest, emitter);
        }, FluxSink.OverflowStrategy.ERROR);
    }

    void resolveAuthentication(AuthenticationRequest<String, String> authenticationRequest,
            FluxSink<AuthenticationResponse> emitter) {
        Optional<UserEntity> userEntityOptional = userService.findByEmail(authenticationRequest.getIdentity());

        if (userEntityOptional.isEmpty()) {
            emitter.next(AuthenticationResponse.failure(AuthenticationFailureReason.USER_NOT_FOUND));
            emitter.complete();
            return;
        }

        UserEntity userEntity = userEntityOptional.get();
        if (passwordEncoder.matches(authenticationRequest.getSecret(), userEntity.getHashedPassword())) {
            emitter.next(AuthenticationResponse.success(
                    (String) authenticationRequest.getIdentity(),
                    getAuthorities(userEntity)));
            emitter.complete();
        } else {
            emitter.next(AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
            emitter.complete();
        }
    }

    private List<String> getAuthorities(UserEntity userEntity) {
        List<String> authorities = new ArrayList<>();
        if (userEntity.getRoles() == null) {
            return authorities;
        }

        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add("ROLE_" + role.getName());
            if (role.getPermissions() != null) {
                for (PermissionEntity perm : role.getPermissions()) {
                    authorities.add(perm.getName());
                }
            }
        }

        return authorities;
    }

}
