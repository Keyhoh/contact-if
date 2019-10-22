package com.demo.contactif.infrastructure.application;

import com.demo.contactif.domain.application.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ContactKey {
    @Id
    @Column(length = 36)
    @NotNull
    @Size(min = 36, max = 36)
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    private User user;
}
