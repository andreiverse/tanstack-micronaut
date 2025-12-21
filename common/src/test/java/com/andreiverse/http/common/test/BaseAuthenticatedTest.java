package com.andreiverse.http.common.test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

/**
 * Base class for authenticated endpoint tests.
 * Test users are automatically seeded via database migration
 * V999__test_users.sql
 */
@MicronautTest(transactional = false)
public abstract class BaseAuthenticatedTest {
    // Test users are seeded by migration and available for all tests
}
