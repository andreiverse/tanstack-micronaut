package com.andreiverse.demo.security;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.inject.Singleton;

@Singleton
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String plain, String encoded) {
        return BCrypt.checkpw(plain, encoded);
    }

}
