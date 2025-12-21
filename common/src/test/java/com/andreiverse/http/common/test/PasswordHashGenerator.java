package com.andreiverse.http.common.test;

import com.andreiverse.http.common.security.authentication.BCryptPasswordEncoder;

/**
 * Utility to generate BCrypt password hashes for test users.
 * Run this to get the hashes for the SQL scripts.
 */
public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("admin1pass: " + encoder.encode("admin1pass"));
        System.out.println("admin2pass: " + encoder.encode("admin2pass"));
        System.out.println("member1pass: " + encoder.encode("member1pass"));
        System.out.println("member2pass: " + encoder.encode("member2pass"));
    }
}
