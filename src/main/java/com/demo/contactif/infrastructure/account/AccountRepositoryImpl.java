package com.demo.contactif.infrastructure.account;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.account.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(account);
    }
}
