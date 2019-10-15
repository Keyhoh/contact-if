package com.demo.contactif.infrastructure.account;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Embeddable
public class Account implements Serializable {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();
}