package com.andreiverse.http.common.entity;

import java.util.Objects;

import com.andreiverse.http.common.security.permission.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@lombok.NoArgsConstructor
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
