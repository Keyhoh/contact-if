package com.demo.contactif.domain.security.password;

import com.google.common.base.Strings;

import javax.validation.constraints.NotBlank;

public class Password {
    private Password(String p) {
        this.p = p;
    }

    public static Password of(String p) {
        if (Strings.isNullOrEmpty(p)) {
            throw new IllegalArgumentException();
        }
        return new Password(p);
    }

    @NotBlank
    private String p;

    public String value() {
        return p;
    }
}
