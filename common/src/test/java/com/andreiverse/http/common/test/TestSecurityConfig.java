package com.andreiverse.http.common.test;

/**
 * Test security configuration with roles and permissions for testing.
 */
public class TestSecurityConfig {

    // Test Permissions
    public static final String PERM_USER_READ = "user:read";
    public static final String PERM_USER_WRITE = "user:write";
    public static final String PERM_ARTICLE_READ = "article:read";
    public static final String PERM_ARTICLE_WRITE = "article:write";

    // Test Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MEMBER = "MEMBER";

    // Test User Constants - Admins
    public static final String ADMIN1_EMAIL = "admin1@test.com";
    public static final String ADMIN1_PASSWORD = "admin1pass";

    public static final String ADMIN2_EMAIL = "admin2@test.com";
    public static final String ADMIN2_PASSWORD = "admin2pass";

    // Test User Constants - Members
    public static final String MEMBER1_EMAIL = "member1@test.com";
    public static final String MEMBER1_PASSWORD = "member1pass";

    public static final String MEMBER2_EMAIL = "member2@test.com";
    public static final String MEMBER2_PASSWORD = "member2pass";

    /**
     * Get admin role permissions.
     */
    public static String[] getAdminPermissions() {
        return new String[] {
                PERM_USER_READ,
                PERM_USER_WRITE,
                PERM_ARTICLE_READ,
                PERM_ARTICLE_WRITE
        };
    }

    /**
     * Get member role permissions.
     */
    public static String[] getMemberPermissions() {
        return new String[] {
                PERM_ARTICLE_READ
        };
    }
}
