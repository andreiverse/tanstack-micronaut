package com.andreiverse.demo.controller;

import java.util.Map;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("security")
@RequiredArgsConstructor
public class SecurityController extends BaseController {

    private final SessionStore<Session> sessionStore;

    @Get("success")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public Map<String, Object> successAuthentication(Authentication authentication) {
        return Map.of("message", "You are authenticated", "username", authentication.getName());
    }

    @Get("failure")
    @Status(HttpStatus.UNAUTHORIZED)
    @Secured(SecurityRule.IS_ANONYMOUS)
    public void failureAuthentication() {
        throw new AuthorizationException(null);
    }

    @Post("/logout")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Map<String, Object> logout(HttpRequest<?> request, Session session) {
        // this actually deletes the session, but it keeps the cookie until the user
        // relogins
        sessionStore.deleteSession(session.getId());
        return Map.of("message", "Logged out successfully");
    }

}
