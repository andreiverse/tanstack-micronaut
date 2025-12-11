package com.andreiverse.demo.controller;

import com.andreiverse.demo.domain.UserRegistrationRequest;
import com.andreiverse.demo.entity.UserEntity;
import com.andreiverse.demo.service.UserService;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("users")
public class UserController extends BaseController {

    private final UserService userService;

    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public UserEntity register(@Body UserRegistrationRequest userRegistrationRequest) {
        return userService.register(userRegistrationRequest);
    }

}
