-- Create user_details table that extends users with server-specific fields
CREATE TABLE user_details (
    user_id UUID PRIMARY KEY,
    description VARCHAR(1000) NOT NULL DEFAULT '',
    CONSTRAINT fk_user_details_user 
        FOREIGN KEY (user_id) 
        REFERENCES users(id) 
        ON DELETE CASCADE
);

-- Create index for faster lookups
CREATE INDEX idx_user_details_user_id ON user_details(user_id);