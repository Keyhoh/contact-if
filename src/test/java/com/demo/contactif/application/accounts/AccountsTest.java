package com.demo.contactif.application.accounts;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.account.AccountRepository;
import com.demo.contactif.domain.application.email.EmailAddress;
import com.demo.contactif.domain.application.user.User;
import com.demo.contactif.domain.security.password.Password;
import com.demo.contactif.infrastructure.application.ContactKeyRepository;
import com.demo.contactif.infrastructure.application.UserRepository;
import com.demo.contactif.infrastructure.security.Authentication;
import com.demo.contactif.infrastructure.security.AuthenticationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountsTest {
    private Accounts accounts;
    @Mock
    AccountRepository accountRepository;
    @Mock
    AuthenticationRepository authenticationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ContactKeyRepository contactKeyRepository;

    void initializeAccounts() {
        accounts = new Accounts(accountRepository, authenticationRepository, userRepository, contactKeyRepository);
    }

    @Test
    void postAccountTest() {
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(authenticationRepository.save(any(Authentication.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        initializeAccounts();

        assertDoesNotThrow(() -> accounts.postAccount(EmailAddress.of("test@dev.com"), Password.of("password")));
        Account account = accounts.postAccount(EmailAddress.of("test@dev.com"), Password.of("password"));
        assertNotNull(account);
        assertNotNull(account.getId());
        assertDoesNotThrow(() -> UUID.fromString(account.getId()));
    }
}
