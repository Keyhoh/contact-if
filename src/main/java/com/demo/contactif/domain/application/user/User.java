package com.demo.contactif.domain.application.user;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.email.EmailAddress;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    public User(@NonNull Account account, @NonNull EmailAddress emailAddress) {
        this.account = account;
        this.emailAddress = emailAddress.value;
    }

    @Id
    @Column(length = 36)
    @Size(min = 36, max = 36)
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @MapsId
    private Account account;
    @Column(unique = true)
    @Email
    private String emailAddress;
}
