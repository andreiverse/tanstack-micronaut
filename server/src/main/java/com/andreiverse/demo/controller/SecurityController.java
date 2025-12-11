package com.andreiverse.demo.controller;

import java.util.Map;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

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

}
