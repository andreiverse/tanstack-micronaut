-- Insert test users for authentication tests
-- Password hashes are BCrypt hashed versions of the passwords defined in TestSecurityConfig
-- admin1pass: $2a$10$IY3Vbzyk/CgQ2GSB9H88gOC9W0frPWNmx5RzhNFBrffTUAuV8mtem
-- admin2pass: $2a$10$S.hwLQQa/JLB2LcdhjVGg.PzsOo4w7IgX.tbhZ0RmEHwWEC9nv8l.
-- member1pass: $2a$10$I49A3chGpS3nTMDoPvOJ2O5IpMoOEkl1jGBSAMwbUO7KKiSivJQqG
-- member2pass: $2a$10$YY.MVkwQVqbdFfa0egSKDukAdvzEdaRMP8nl5Vr0C3B6xWMdOSUHW

-- Insert admin users
INSERT INTO users (id, email, hashed_password, created_at, updated_at) VALUES
    ('00000000-0000-0000-0000-000000000001', 'admin1@test.com', '$2a$10$IY3Vbzyk/CgQ2GSB9H88gOC9W0frPWNmx5RzhNFBrffTUAuV8mtem', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('00000000-0000-0000-0000-000000000002', 'admin2@test.com', '$2a$10$S.hwLQQa/JLB2LcdhjVGg.PzsOo4w7IgX.tbhZ0RmEHwWEC9nv8l.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert member users
INSERT INTO users (id, email, hashed_password, created_at, updated_at) VALUES
    ('00000000-0000-0000-0000-000000000003', 'member1@test.com', '$2a$10$I49A3chGpS3nTMDoPvOJ2O5IpMoOEkl1jGBSAMwbUO7KKiSivJQqG', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('00000000-0000-0000-0000-000000000004', 'member2@test.com', '$2a$10$YY.MVkwQVqbdFfa0egSKDukAdvzEdaRMP8nl5Vr0C3B6xWMdOSUHW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
