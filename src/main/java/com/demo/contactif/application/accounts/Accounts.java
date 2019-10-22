package com.demo.contactif.application.accounts;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.account.AccountService;
import com.demo.contactif.domain.application.email.EmailAddress;
import com.demo.contactif.domain.application.user.User;
import com.demo.contactif.domain.security.password.Password;
import com.demo.contactif.infrastructure.application.UserRepository;
import com.demo.contactif.infrastructure.security.Authentication;
import com.demo.contactif.infrastructure.security.AuthenticationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Accounts {
    private final AccountService accountService;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;

    public Accounts(AccountService accountService, AuthenticationRepository authenticationRepository, UserRepository userRepository) {
        this.accountService = accountService;
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Account postAccount(EmailAddress emailAddress, Password password) {
        Account account = accountService.create();
        authenticationRepository.save(new Authentication(account, password));
        userRepository.save(new User(account, emailAddress));
        return account;
    }
}
