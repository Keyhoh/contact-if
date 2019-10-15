package com.demo.contactif.domain.security;

public enum Role {
    USER("ROLE_USER"), ADMIN("USER_ADMIN");

    public final String value;

    Role(String value) {
        this.value = value;
    }
}
