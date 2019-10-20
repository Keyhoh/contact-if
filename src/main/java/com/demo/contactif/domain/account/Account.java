package com.demo.contactif.domain.account;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Embeddable
public class Account implements Serializable {
    @Id
    @Column(length = 36)
    @NotNull
    @Size(min = 36, max = 36)
    private String id = UUID.randomUUID().toString();
}
