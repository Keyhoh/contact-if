package com.demo.contactif.presentation;

import com.demo.contactif.application.accounts.Accounts;
import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.email.EmailAddress;
import com.demo.contactif.domain.security.password.Password;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final Accounts accounts;

    public AccountsController(Accounts accounts) {
        this.accounts = accounts;
    }

    @PostMapping
    @ResponseBody
    public Account postAccount(@RequestParam("email") @Email String emailAddress, @RequestParam("password") @NotBlank String password) {
        return accounts.postAccount(EmailAddress.of(emailAddress), Password.of(password));
    }

    @DeleteMapping("{id}")
    public void deleteAccount(Principal principal, @PathVariable String id) {
        if (Objects.equals(principal.getName(), id)) {
            accounts.deleteAccount(id);
        }
    }
}
