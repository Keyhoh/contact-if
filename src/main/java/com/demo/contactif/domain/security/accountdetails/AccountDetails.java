package com.demo.contactif.domain.security.accountdetails;

import com.demo.contactif.domain.security.password.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@AllArgsConstructor
public class AccountDetails implements UserDetails {
    @Getter(onMethod = @__(@Override))
    @NotNull
    @Size(min = 36, max = 36)
    private String username;
    @NotBlank
    private Password password;
    @Getter(onMethod = @__(@Override))
    private Collection<GrantedAuthority> authorities;
    @Getter(onMethod = @__(@Override))
    private boolean enabled;

    @Override
    public String getPassword() {
        return password.value;
    }

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
