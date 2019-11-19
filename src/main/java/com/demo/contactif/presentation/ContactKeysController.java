package com.demo.contactif.presentation;

import com.demo.contactif.application.accounts.ContactKeys;
import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.contactkey.ContactKey;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/contactKeys")
public class ContactKeysController {
    private final ContactKeys contactKeys;

    public ContactKeysController(ContactKeys contactKeys) {
        this.contactKeys = contactKeys;
    }

    @PostMapping
    public ContactKey postContactKey(@NonNull Principal principal) {
        return contactKeys.postContactKey(Account.of(principal));
    }

    @DeleteMapping("{id}")
    public void deleteContactKey(@NonNull Principal principal, @PathVariable String id) {
        contactKeys.deleteContactKey(id, Account.of(principal));
    }
}
