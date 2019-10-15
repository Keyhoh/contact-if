package com.demo.contactif.infrastructure.application;

import com.demo.contactif.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(length = 36)
    private String id;
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Account account;
    @Email
    @Column(unique = true)
    private String emailAddress;

    public User(Account account, @Email String emailAddress) {
        this.account = account;
        this.emailAddress = emailAddress;
    }
}
