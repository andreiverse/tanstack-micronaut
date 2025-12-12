package com.andreiverse.demo.domain;

import io.micronaut.serde.annotation.Serdeable.Deserializable;
import lombok.Data;

@Data
@Deserializable
public class UserRegistrationRequest {
    private String email;
    private String password;
    private String testFields;
}
