package com.demo.contactif.domain.security.accountdetails;

import java.util.Optional;

public interface AccountDetailsRepository {
    Optional<AccountDetails> findById(String id);
}
