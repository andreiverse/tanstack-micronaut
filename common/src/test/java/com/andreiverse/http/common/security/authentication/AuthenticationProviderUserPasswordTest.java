package com.andreiverse.http.common.security.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import com.andreiverse.http.common.entity.RoleEntity;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import reactor.test.StepVerifier;

class AuthenticationProviderUserPasswordTest {

    private AuthenticationProviderUserPassword<Object> authenticationProvider;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        passwordEncoder = mock(PasswordEncoder.class);
        userService = mock(UserService.class);
        authenticationProvider = new AuthenticationProviderUserPassword<>(passwordEncoder, userService);
    }

    @Test
    void testAuthenticationSuccess() {
        String email = "admin@example.com";
        String password = "password";
        String hashedPassword = "hashedPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setHashedPassword(hashedPassword);
        RoleEntity role = new RoleEntity();
        role.setName("ADMIN");
        userEntity.setRoles(List.of(role));

        when(userService.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(true);

        AuthenticationRequest<String, String> request = mock(AuthenticationRequest.class);
        when(request.getIdentity()).thenReturn(email);
        when(request.getSecret()).thenReturn(password);

        Publisher<AuthenticationResponse> responsePublisher = authenticationProvider.authenticate(null, request);

        StepVerifier.create(responsePublisher)
                .assertNext(response -> {
                    assertTrue(response.isAuthenticated());
                    assertEquals(email, response.getAuthentication().orElseThrow().getName());
                    assertTrue(response.getAuthentication().orElseThrow().getRoles().contains("ROLE_ADMIN"));
                })
                .verifyComplete();
    }

    @Test
    void testAuthenticationInvalidPassword() {
        String email = "admin@example.com";
        String password = "wrongPassword";
        String hashedPassword = "hashedPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setHashedPassword(hashedPassword);

        when(userService.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(false);

        AuthenticationRequest<String, String> request = mock(AuthenticationRequest.class);
        when(request.getIdentity()).thenReturn(email);
        when(request.getSecret()).thenReturn(password);

        Publisher<AuthenticationResponse> responsePublisher = authenticationProvider.authenticate(null, request);

        StepVerifier.create(responsePublisher)
                .assertNext(response -> {
                    assertTrue(!response.isAuthenticated());
                    assertTrue(response instanceof AuthenticationFailed);
                    AuthenticationFailed failed = (AuthenticationFailed) response;
                    assertEquals(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH, failed.getReason());
                })
                .verifyComplete();
    }

    @Test
    void testAuthenticationUserNotFound() {
        String email = "nonexistent@example.com";
        String password = "password";

        when(userService.findByEmail(email)).thenReturn(Optional.empty());

        AuthenticationRequest<String, String> request = mock(AuthenticationRequest.class);
        when(request.getIdentity()).thenReturn(email);
        when(request.getSecret()).thenReturn(password);

        Publisher<AuthenticationResponse> responsePublisher = authenticationProvider.authenticate(null, request);

        StepVerifier.create(responsePublisher)
                .assertNext(response -> {
                    assertTrue(!response.isAuthenticated());
                    assertTrue(response instanceof AuthenticationFailed);
                    AuthenticationFailed failed = (AuthenticationFailed) response;
                    assertEquals(AuthenticationFailureReason.USER_NOT_FOUND, failed.getReason());
                })
                .verifyComplete();
    }
}
