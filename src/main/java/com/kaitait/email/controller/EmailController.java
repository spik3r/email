package com.kaitait.email.controller;

import com.kaitait.email.documentation.EmailControllerDocumentation;
import com.kaitait.email.service.EmailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/api")
@Api(value = "EmailController")
public class EmailController implements EmailControllerDocumentation {

    private final EmailService emailService;

    @Autowired
    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public ResponseEntity<String> index(final String shouldExplode) {
        log.info("/ called with param: {}", shouldExplode);
        if (null != shouldExplode && shouldExplode.equals("true")) {
            throw new RuntimeException("KABOOOOMN");
        }
        return new ResponseEntity<>("Index called!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> sendMail() {
        log.info("/mail called");
        emailService.sendEmail();
        return new ResponseEntity<>("Email Sent!", HttpStatus.CREATED);
    }
}
