package com.andreiverse.demo.domain;

import io.micronaut.serde.annotation.Serdeable.Serializable;
import lombok.Data;

@Data
@Serializable
public class ApiErrorResponse {
    private final String exception;
    private final String message;
}
