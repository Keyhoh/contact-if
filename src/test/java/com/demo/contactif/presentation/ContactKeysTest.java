package com.demo.contactif.presentation;

import com.demo.contactif.application.accounts.Accounts;
import com.demo.contactif.application.accounts.ContactKeys;
import com.demo.contactif.domain.account.Account;
import com.demo.contactif.domain.application.contactkey.ContactKey;
import com.demo.contactif.domain.application.email.EmailAddress;
import com.demo.contactif.domain.security.password.Password;
import com.demo.contactif.exception.NotFoundInStoreException;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DBRider
@DBUnit(url = "jdbc:mysql://localhost:3306/contact_if_test_db?serverTimezone=UTC", driver = "com.mysql.cj.jdbc.Driver", user = "test_user", password = "test_user")
@DataSet("full.xml")
class ContactKeysTest {
    @Autowired
    private ContactKeys contactKeys;
    @Autowired
    private Accounts accounts;

    @Mock
    private Principal principal;

    private final String USER_ID = "0b60eb02-59b3-4ad7-abc4-143848c7edad";

    @Test
    void postContactKeyTest() {
        Account account = accounts.postAccount(EmailAddress.of("test@dev.com"), Password.of("password"));
        List<String> contactKeyList = contactKeys.getContactKeysByUserId(account.getId());
        assertTrue(contactKeyList.isEmpty());
        ContactKey contactKey = contactKeys.postContactKey(account);
        contactKeyList = contactKeys.getContactKeysByUserId(account.getId());
        assertFalse(contactKeyList.isEmpty());
        assertTrue(contactKeyList.contains(contactKey.getId()));
    }

    @Test
    void getContactKeysTest() {
        List<String> contactKeyList = contactKeys.getContactKeysByUserId(USER_ID);
        assertEquals(contactKeyList.size(), 5);
    }

    @Test
    void deleteContactKeysTest() {
        String contactKeyOfUser = "2cbedae6-9e76-4e19-bd0c-c87e632aedce";
        List<String> contactKeyListBeforeDelete = contactKeys.getContactKeysByUserId(USER_ID);
        when(principal.getName()).thenReturn(USER_ID);
        contactKeys.deleteContactKey(contactKeyOfUser, Account.of(principal));
        List<String> contactKeyListAfterDelete = contactKeys.getContactKeysByUserId(USER_ID);
        assertFalse(contactKeyListBeforeDelete.isEmpty());
        assertEquals(contactKeyListBeforeDelete.size() - 1, contactKeyListAfterDelete.size());
    }

    @Test
    void deleteContactKeysByOtherUserTest() {
        String contactKeyOfOtherUsers = "1556b108-6755-42ef-ada1-00fea78043b9";
        when(principal.getName()).thenReturn(USER_ID);
        assertThrows(
                NotFoundInStoreException.class,
                () -> contactKeys.deleteContactKey(contactKeyOfOtherUsers, Account.of(principal))
        );
    }

    @Test
    void deleteContactKeysByNonExistentUserTest() {
        String nonExistentUserId = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee";
        String anyContactKey = "2daa76e2-b101-45a4-bd95-0da46b4ad28c";
        when(principal.getName()).thenReturn(nonExistentUserId);
        assertThrows(
                NotFoundInStoreException.class,
                () -> contactKeys.deleteContactKey(anyContactKey, Account.of(principal))
        );
    }
}
