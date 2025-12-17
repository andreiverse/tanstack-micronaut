package com.andreiverse.demo.controller;

import com.andreiverse.demo.entity.UserDetailsEntity;
import com.andreiverse.demo.service.AppUserDetailsService;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("details")
public class UserDetailsController {

    private final AppUserDetailsService appUserDetailsService;
    private final UserService userService;

    @Get("/current")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public UserDetailsEntity getCurrentUserDetails(Authentication authentication) {
        UserEntity userEntity = this.userService.findByEmail(authentication.getName()).orElseThrow();

        return this.appUserDetailsService.getUserDetails(userEntity);
    }
}
