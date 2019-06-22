package com.kaitait.email.service;

import com.kaitait.email.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    private final EmailService emailService;

    public Consumer(final EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public User consume(final User user) {
        log.info("#### -> Consumed user: -> {}", user.toString());

        emailService.registerNewUser(user);
        return user;
    }

}
