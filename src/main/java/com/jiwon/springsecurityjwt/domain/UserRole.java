package com.jiwon.springsecurityjwt.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
