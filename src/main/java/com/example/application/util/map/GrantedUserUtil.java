package com.example.application.util.map;

import com.example.application.model.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface GrantedUserUtil {
    static GrantedAuthority toGrantedAuthority(RoleEntity roleEntity) {
        if (roleEntity == null) return null;
        String role = roleEntity.getCode();
        role = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return new SimpleGrantedAuthority(role);
    }

    static Set<GrantedAuthority> toGrantedAuthorities(Collection<RoleEntity> collection) {
        return collection.stream().map(roleEntity -> toGrantedAuthority(roleEntity)).collect(Collectors.toSet());
    }

    static String toString(Collection<? extends GrantedAuthority> collections) {
        return collections.stream().map(collection -> collection.getAuthority()).collect(Collectors.joining(","));
    }
}

