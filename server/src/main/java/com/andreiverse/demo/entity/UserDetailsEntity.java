package com.andreiverse.demo.entity;

import org.hibernate.annotations.ColumnDefault;

import com.andreiverse.http.common.entity.BaseUserDetailsEntity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class UserDetailsEntity extends BaseUserDetailsEntity {

    @Column(name = "description", nullable = false)
    @ColumnDefault("''")
    private String description;
}
