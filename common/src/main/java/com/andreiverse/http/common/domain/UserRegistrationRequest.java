package com.andreiverse.http.common.domain;

import io.micronaut.serde.annotation.Serdeable.Deserializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Deserializable
@AllArgsConstructor
public class UserRegistrationRequest {
    private String email;
    private String password;
}
