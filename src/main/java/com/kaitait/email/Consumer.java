package com.kaitait.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@Slf4j
public class Consumer {

    private final EmailService emailService;

    public Consumer(final EmailService emailService) {
        this.emailService = emailService;
    }

    //    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public User consume(final User user) throws IOException, MessagingException {
        log.warn("#### -> Consumed user: -> {}", user.toString());

        emailService.sendEmail(user);
        return user;
    }

}
