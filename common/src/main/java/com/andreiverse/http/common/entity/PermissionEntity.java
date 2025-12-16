package com.andreiverse.http.common.entity;

import java.util.Objects;

import com.andreiverse.http.common.security.authorization.Permission;

import io.micronaut.serde.annotation.Serdeable.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serializable
public class PermissionEntity {
    @Id
    @Column(name = "name", nullable = false, updatable = false, unique = true, length = 100)
    String name;

    public static PermissionEntity from(Permission permission) {
        return new PermissionEntity(permission.getName());
    }

    public String getName() {
        return name;
    }

    // equality by id string
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PermissionEntity))
            return false;
        PermissionEntity that = (PermissionEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
