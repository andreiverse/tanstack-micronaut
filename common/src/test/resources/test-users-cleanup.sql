-- Cleanup test users after authentication tests

DELETE FROM users WHERE email IN (
    'admin1@test.com',
    'admin2@test.com',
    'member1@test.com',
    'member2@test.com'
);
