package com.demo.contactif.domain.application.contactkey;

import com.demo.contactif.domain.application.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
