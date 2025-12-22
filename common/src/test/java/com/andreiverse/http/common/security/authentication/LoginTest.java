package com.andreiverse.http.common.security.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@MicronautTest
@Slf4j
public class LoginTest {

    @Inject
    @Client("/")
    private HttpClient webClient;

    @Test
    void testLoginValidCredentials() {
        HttpResponse<Object> response = webClient.toBlocking().exchange(HttpRequest.POST("/login",
                Map.of("username", "admin1@test.com", "password", "admin1pass")));

        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void testLoginInvalidCredentials() {
        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
                () -> webClient.toBlocking().exchange(HttpRequest.POST("/login",
                        Map.of("username", "admin1@test.com", "password", "invalidpass"))));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }
}
