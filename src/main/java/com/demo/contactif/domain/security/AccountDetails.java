package com.demo.contactif.domain.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class AccountDetails implements UserDetails {
    @Getter(onMethod = @__(@Override))
    private String username;
    @Getter(onMethod = @__(@Override))
    private String password;
    @Getter(onMethod = @__(@Override))
    private Collection<GrantedAuthority> authorities;
    @Getter(onMethod = @__(@Override))
    private boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
