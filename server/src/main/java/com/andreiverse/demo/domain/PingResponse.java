package com.andreiverse.demo.domain;

import java.time.Instant;

import io.micronaut.serde.annotation.Serdeable.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Serializable
public class PingResponse {
    @NotNull
    private boolean ok = true;
    @NotNull
    private Instant timestamp = Instant.now();
}