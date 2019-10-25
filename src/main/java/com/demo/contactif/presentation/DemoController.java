package com.demo.contactif.presentation;

import com.demo.contactif.domain.application.contactkey.ContactKey;
import com.demo.contactif.infrastructure.application.ContactKeyRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.security.Principal;

@Controller
public class DemoController {
    private final ContactKeyRepository contactKeyRepository;
    private final MailSender mailSender;

    public DemoController(ContactKeyRepository contactKeyRepository, MailSender mailSender) {
        this.contactKeyRepository = contactKeyRepository;
        this.mailSender = mailSender;
    }

    @GetMapping("/mypage")
    public String index(Principal principal, Model model) {
        model.addAttribute("userId", principal.getName());
        model.addAttribute("contactKeyList", contactKeyRepository.findByUserId(principal.getName()));
        return "/myPage.html";
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
}
