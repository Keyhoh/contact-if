package com.demo.contactif.infrastructure.application;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ContactKey {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    private User user;
}
