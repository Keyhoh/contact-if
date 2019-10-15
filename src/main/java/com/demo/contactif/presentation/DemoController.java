package com.demo.contactif.presentation;

import com.demo.contactif.application.accounts.Accounts;
import com.demo.contactif.infrastructure.application.ContactKey;
import com.demo.contactif.infrastructure.application.ContactKeyRepository;
import com.demo.contactif.infrastructure.application.User;
import com.demo.contactif.infrastructure.application.UserRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

@Controller
public class DemoController {
    private final Accounts accounts;
    private final UserRepository userRepository;
    private final ContactKeyRepository contactKeyRepository;
    private final MailSender mailSender;

    public DemoController(Accounts accounts, UserRepository userRepository, ContactKeyRepository contactKeyRepository, MailSender mailSender) {
        this.accounts = accounts;
        this.userRepository = userRepository;
        this.contactKeyRepository = contactKeyRepository;
        this.mailSender = mailSender;
    }

    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        model.addAttribute("userId", principal.getName());
        return "/list.html";
    }

    @PostMapping("/accounts")
    @ResponseBody
    public String postAccount(@RequestParam("email") @Email String emailAddress, @RequestParam("password") @NotBlank String password) {
        return accounts.postAccount(emailAddress, password).getId();
    }

    @GetMapping("/contactKey")
    @ResponseBody
    public String postContactKey(Principal principal) {
        User user = userRepository.findById(principal.getName()).orElseThrow();
        ContactKey contactKey = new ContactKey();
        contactKey.setUser(user);
        contactKeyRepository.save(contactKey);
        return contactKey.getId();
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public void sendMail(@RequestParam("contactKeyId") @NotBlank String contactKeyId, @RequestParam("text") String text) {
        ContactKey contactKey = contactKeyRepository.findById(contactKeyId).orElseThrow();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(contactKey.getUser().getEmailAddress());
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }

    @GetMapping("/contactKeys")
    @ResponseBody
    public List getContactKeys(Principal principal) {
        return contactKeyRepository.findByUserId(principal.getName());
    }
}
