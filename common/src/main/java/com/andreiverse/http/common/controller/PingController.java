package com.andreiverse.http.common.controller;

import com.andreiverse.http.common.domain.PingResponse;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("ping")
@Secured(SecurityRule.IS_ANONYMOUS)
public class PingController extends BaseController {

    @Get
    public PingResponse ping() {
        return new PingResponse();
    }
}
