package com.andreiverse.http.common.domain;

import io.micronaut.serde.annotation.Serdeable.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
@Serializable
public class ApiErrorResponse {
    @NotNull
    private final String exception;
    @NonNull
    private final String message;
}
