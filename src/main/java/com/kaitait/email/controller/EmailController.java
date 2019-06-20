package com.kaitait.email.controller;

import com.kaitait.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


@Slf4j
@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping({"/{shouldExplode}", "/"})
    public String index(@PathVariable(required = false) final String shouldExplode) {
        log.info("/ called with param: {}", shouldExplode);
        if (null !=shouldExplode && shouldExplode.equals("true")) {
            throw new RuntimeException("KABOOOOMN");
        }
        return "Index called!";
    }

    @GetMapping("/mail")
    public String sendMail() throws MessagingException {
        log.info("/mail called");
        emailService.sendEmail();
        return "Email Sent!";
    }
}
