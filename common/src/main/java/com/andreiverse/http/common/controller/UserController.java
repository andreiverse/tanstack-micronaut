package com.andreiverse.http.common.controller;

import com.andreiverse.http.common.annotation.PaginationParams;
import com.andreiverse.http.common.domain.UserRegistrationRequest;
import com.andreiverse.http.common.entity.UserEntity;
import com.andreiverse.http.common.service.UserService;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Parameter;
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
            @QueryValue(defaultValue = "0") int page,
            @QueryValue(defaultValue = "20") int size,
            @QueryValue @Nullable String sort) {
        Pageable pageable;
        if (sort != null && !sort.isBlank()) {
            // Parse sort parameter: property,direction (e.g., "email,asc" or "email,desc")
            String[] sortParts = sort.split(",");
            String property = sortParts[0];
            boolean isAscending = !(sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1]));

            // Create case-sensitive sort order to prevent Hibernate from applying lower()
            // to non-string fields
            Sort.Order order = new Sort.Order(property,
                    isAscending ? Sort.Order.Direction.ASC : Sort.Order.Direction.DESC, false);
            pageable = Pageable.from(page, size, Sort.of(order));
        } else {
            pageable = Pageable.from(page, size);
        }
        return userService.findAll(pageable);
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
