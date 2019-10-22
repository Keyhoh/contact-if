package com.demo.contactif.domain.security.password;

import com.google.common.base.Strings;

import javax.validation.constraints.NotBlank;

public class Password {
    private Password(String value) {
        this.value = value;
    }

    public static Password of(String value) {
        if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException();
        }
        return new Password(value);
    }

    @NotBlank
    public final String value;
}
