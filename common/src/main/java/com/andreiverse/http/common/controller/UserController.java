package com.andreiverse.http.common.controller;

import com.andreiverse.http.common.domain.PaginationRequest;
import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import io.micronaut.data.model.Page;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.rules.SecurityRule;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("users")
@Tag(name = "Users", description = "User management endpoints")
public class UserController extends BaseController {

    private final UserService userService;

    @Get
    @Secured("ROLE_ADMIN")
    public Page<UserEntity> getAllUsers(
            @RequestBean PaginationRequest request) {
        return userService.findAll(request.toPageable());
    }

    @Get("current")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public UserEntity getCurrentUser(Authentication authentication) {
        var user = userService.findByEmail(authentication.getName());
        return user.orElseThrow(() -> new AuthorizationException(authentication));
    }

    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Status(HttpStatus.CREATED)
    public UserEntity register(@Body UserRegistrationRequest userRegistrationRequest) {
        return userService.register(userRegistrationRequest);
    }

}
