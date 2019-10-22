package com.demo.contactif.infrastructure.security;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.security.Role;
import com.demo.contactif.domain.security.password.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {
    public Authentication(Account account, Password password) {
        this.account = account;
        this.password = new BCryptPasswordEncoder().encode(password.value);
    }

    @Id
    @Column(length = 36)
    @Size(min = 36, max = 36)
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @MapsId
    private Account account;
    @Column(nullable = false)
    @NotNull
    private String password;
    @Column(nullable = false)
    private boolean enabled = true;
    @Column(nullable = false)
    private boolean admin = false;

    Collection<GrantedAuthority> getAuthorities() {
        if (enabled) {
            return AuthorityUtils.createAuthorityList(Role.USER.value, Role.ADMIN.value);
        } else {
            return AuthorityUtils.createAuthorityList(Role.USER.value);
        }
    }
}
