package com.andreiverse.http.common.controller;

import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("users")
public class UserController extends BaseController {

    private final UserService userService;

    @Get("current")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public UserEntity getCurrentUser(Authentication authentication) {
        var user = userService.findByEmail(authentication.getName());
        return user.orElseThrow(() -> new AuthorizationException(authentication));
    }

    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public UserEntity register(@Body UserRegistrationRequest userRegistrationRequest) {
        return userService.register(userRegistrationRequest);
    }

}
