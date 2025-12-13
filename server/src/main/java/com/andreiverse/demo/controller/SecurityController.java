package com.andreiverse.demo.controller;

import java.util.Map;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import io.micronaut.session.http.SessionForRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("security")
public class SecurityController extends BaseController {

    @Get("success")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public Map<String, Object> successAuthentication(Authentication authentication) {
        return Map.of("message", "You are authenticated", "username", authentication.getName());
    }

    @Get("failure")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Map<String, Object> failureAuthentication() {
        return Map.of("message", "You are not authenticated");
    }

    @Post("/logout")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Map<String, Object> logout(HttpRequest<?> request) {
        Session session = SessionForRequest.find(request).orElse(null);
        if (session != null) {
            for (String key : session.names()) {
                session.remove(key);
            }
        }
        session.clear();
        return Map.of("message", "Logged out successfully");
    }

}
