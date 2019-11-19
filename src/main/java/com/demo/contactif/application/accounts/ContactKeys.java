package com.demo.contactif.application.accounts;

import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.contactkey.ContactKey;
import com.demo.contactif.domain.application.user.User;
import com.demo.contactif.exception.NotFoundInStoreException;
import com.demo.contactif.infrastructure.application.ContactKeyRepository;
import com.demo.contactif.infrastructure.application.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactKeys {
    private final ContactKeyRepository contactKeyRepository;
    private final UserRepository userRepository;

    public ContactKeys(ContactKeyRepository contactKeyRepository, UserRepository userRepository) {
        this.contactKeyRepository = contactKeyRepository;
        this.userRepository = userRepository;
    }

    public List<String> getContactKeysByUserId(String userId) {
        return contactKeyRepository.findByUserId(userId)
                .stream().map(ContactKey::getId)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContactKey postContactKey(@NotBlank Account account) {
        User user = userRepository.findById(account.getId()).orElseThrow();
        ContactKey contactKey = new ContactKey();
        contactKey.setUser(user);
        return contactKeyRepository.save(contactKey);
    }

    @Transactional
    public void deleteContactKey(@NotBlank String id, @NonNull Account account) throws NotFoundInStoreException {
        ContactKey contactKey = contactKeyRepository.findById(id).orElseThrow(NotFoundInStoreException::new);
        if (!account.getId().equals(contactKey.getUser().getId())) {
            throw new NotFoundInStoreException();
        }
        contactKeyRepository.deleteById(id);
    }
}
