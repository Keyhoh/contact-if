package com.demo.contactif.domain.security;

import java.util.Optional;

public interface AccountDetailsRepository {
    Optional<AccountDetails> findById(String id);
}
