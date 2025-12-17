package com.andreiverse.http.common.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base class for user details entities.
 * Provides a one-to-one relationship with UserEntity where userId is both the
 * primary key and foreign key.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public abstract class BaseUserDetailsEntity {

    @Id
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId // This makes userId the PK and FK
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
