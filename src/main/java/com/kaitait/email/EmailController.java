package com.kaitait.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String index() {
        log.info("/ called");
        return "Index called";
    }

    @GetMapping("/explode")
    public String explode() {
        log.info("/explode called");
        throw new RuntimeException("KABOOOOMN");
    }

    @GetMapping("/mail")
    public String sendMail() throws MessagingException {
        log.info("/mail called");
        emailService.sendEmail();
        return "Sent";
    }
}
