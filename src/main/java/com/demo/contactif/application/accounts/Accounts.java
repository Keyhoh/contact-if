package com.demo.contactif.application.accounts;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.email.EmailAddress;
import com.demo.contactif.domain.application.user.User;
import com.demo.contactif.domain.security.password.Password;
import com.demo.contactif.infrastructure.account.AccountRepository;
import com.demo.contactif.infrastructure.application.ContactKeyRepository;
import com.demo.contactif.infrastructure.application.UserRepository;
import com.demo.contactif.infrastructure.security.Authentication;
import com.demo.contactif.infrastructure.security.AuthenticationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Accounts {
    private final AccountRepository accountRepository;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final ContactKeyRepository contactKeyRepository;

    public Accounts(AccountRepository accountRepository, AuthenticationRepository authenticationRepository, UserRepository userRepository, ContactKeyRepository contactKeyRepository) {
        this.accountRepository = accountRepository;
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
        this.contactKeyRepository = contactKeyRepository;
    }

    @Transactional
    public Account postAccount(EmailAddress emailAddress, Password password) {
        Account account = accountRepository.save(new Account());
        authenticationRepository.save(new Authentication(account, password));
        userRepository.save(new User(account, emailAddress));
        return account;
    }

    @Transactional
    public void deleteAccount(String id) {
        contactKeyRepository.deleteAllByUserId(id);
        userRepository.deleteById(id);
    }
}
