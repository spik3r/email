package com.kaitait.email.service

import com.kaitait.email.domain.EmailedUser
import com.kaitait.email.domain.User
import com.kaitait.email.repository.EmailedUserRepository
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification

class EmailServiceSpec extends Specification{

    JavaMailSender javaMailSender
    EmailService emailService
    FakeUserService fakeUserService
    EmailedUserRepository emailedUserRepository
    User mockUser
    EmailedUser emailedUser

    def setup() {
        javaMailSender = Mock()
        fakeUserService = Mock()
        emailedUserRepository = Mock()
        mockUser = new User("John", "Smith", "js@gmail.com","pochahontas123")
        emailedUser = new EmailedUser(mockUser)
        emailService = new EmailService(javaMailSender, fakeUserService, emailedUserRepository)
    }

    def "registerNewUser works as expected when no User is passed in"() {
        when:
        emailService.registerNewUser(mockUser)

        then:
        1 * emailedUserRepository.save(_) >> emailedUser

        and:
        1 * javaMailSender.send(_)
    }

    def "sendEmail works when no User is passed in"() {
        when:
        emailService.sendEmail()

        then:
        1 * fakeUserService.getUser() >> mockUser
        1 * emailedUserRepository.save(_) >> emailedUser
        1 * javaMailSender.send(_)
    }

    def "SendEmail works when User is passed in"() {
        when:
        emailService.sendEmail(emailedUser)

        then:
        1 * javaMailSender.send(_)
    }
}
