package com.demo.contactif.infrastructure.account;

import com.demo.contactif.domain.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
