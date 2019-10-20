package com.demo.contactif.infrastructure.application;

import com.demo.contactif.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    public User(Account account, String emailAddress) {
        this.account = account;
        this.emailAddress = emailAddress;
    }

    @Id
    @Column(length = 36)
    @NotNull
    @Size(min = 36, max = 36)
    private String id;
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Account account;
    @Column(unique = true)
    @Email
    private String emailAddress;
}
