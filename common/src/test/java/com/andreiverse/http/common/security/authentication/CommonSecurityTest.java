package com.andreiverse.http.common.security.authentication;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.service.UserService;

import jakarta.inject.Inject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommonSecurityTest {

    @Inject
    UserService userService;

    @BeforeAll
    void setUp() {
        userService.register(new UserRegistrationRequest("test@test.com", "test123456"));
    }

    @AfterAll
    void cleanUp() {
        userService.findByEmail("test@test.com")
                .ifPresent(user -> userService.deleteUser(user));
    }

}
