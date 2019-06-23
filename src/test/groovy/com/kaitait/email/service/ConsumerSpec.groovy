package com.kaitait.email.service

import com.kaitait.email.domain.User
import spock.lang.Specification

class ConsumerSpec extends Specification {
    Consumer consumer
    EmailService emailService
    User user

    void setup() {
        user = Mock()
        emailService = Mock()
        consumer = new Consumer(emailService)
    }

    def "Consume works as expected"() {
        when:
        consumer.consume(user)

        then:
        1 * emailService.registerNewUser(user)
    }
}
