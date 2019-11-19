package com.demo.contactif.domain.account;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.security.Principal;
import java.util.UUID;

@Entity
@Getter
@Embeddable
public class Account implements Serializable {
    public static Account of(@NonNull Principal principal) {
        Account account = new Account();
        account.id = principal.getName();
        return account;
    }

    @Id
    @Column(length = 36)
    @NotNull
    @Size(min = 36, max = 36)
    private String id = UUID.randomUUID().toString();
}
