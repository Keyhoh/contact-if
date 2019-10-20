package com.demo.contactif.infrastructure.security;

import com.demo.contactif.domain.security.accountdetails.AccountDetails;
import com.demo.contactif.domain.security.accountdetails.AccountDetailsRepository;
import com.demo.contactif.domain.security.password.Password;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountDetailsRepositoryImpl implements AccountDetailsRepository {
    private final AuthenticationRepository authenticationRepository;

    public AccountDetailsRepositoryImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Optional<AccountDetails> findById(String id) {
        return authenticationRepository.findById(id).map(this::toAccountDetails);
    }

    private AccountDetails toAccountDetails(Authentication authentication) {
        return new AccountDetails(
                authentication.getId(),
                Password.of(authentication.getPassword()),
                authentication.getAuthorities(),
                authentication.isEnabled()
        );
    }
}
