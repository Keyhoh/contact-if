package com.demo.contactif.domain.application.email;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.constraints.Email;

public class EmailAddress {
    private EmailAddress(String value) {
        this.value = value;
    }

    public static EmailAddress of(String value) {
        if (!EmailValidator.getInstance().isValid(value)) {
            throw new IllegalArgumentException();
        }
        return new EmailAddress(value);
    }

    @Email
    public final String value;
}
