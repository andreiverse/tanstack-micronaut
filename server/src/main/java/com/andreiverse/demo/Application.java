package com.andreiverse.demo;

import io.micronaut.openapi.annotation.OpenAPISecurity;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(info = @Info(title = "server", version = "1.0.0"))
@OpenAPISecurity
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
