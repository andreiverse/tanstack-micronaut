package com.andreiverse.http.common.domain;

import io.micronaut.serde.annotation.Serdeable.Deserializable;
import lombok.Data;

@Data
@Deserializable
public class UserRegistrationRequest {
    private String email;
    private String password;
}
