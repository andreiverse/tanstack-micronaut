package com.andreiverse.http.common.security;

public interface PasswordEncoder {
    String encode(String plain);

    boolean matches(String plain, String encoded);
}
