package com.kaitait.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    private final Consumer consumer;

    @Autowired
    public EmailController(Consumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/")
    public String index() {

        log.info("index called");

        return "Index called";
    }
}
