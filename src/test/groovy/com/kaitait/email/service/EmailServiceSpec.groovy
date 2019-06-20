package com.kaitait.email.service


import com.kaitait.email.domain.User
import com.kaitait.email.service.EmailService
import com.kaitait.email.service.FakeUserService
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification

import static com.kaitait.email.service.EmailService.FROM_EMAIL

class EmailServiceSpec extends Specification{

    JavaMailSender javaMailSender
    EmailService emailService
    FakeUserService fakeUserService
    User mockUser

    def setup() {
        javaMailSender = Mock()
        fakeUserService = Mock()
        mockUser = new User("John", "Smith", "js@gmail.com","pochahontas123")
        mockUser.setId("mock_user_id")
        emailService = new EmailService(javaMailSender, fakeUserService)
//        fakeUserService.getUser() >> mockUser
    }

    def "sendEmail works as expected when no User is passed in"() {
        given:
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage()
        simpleMailMessage.setFrom(FROM_EMAIL)
        simpleMailMessage.setReplyTo(FROM_EMAIL)
        simpleMailMessage.setTo(mockUser.getEmail())
        simpleMailMessage.setSubject("Hello: John! Welcome to XYZ-service")
        simpleMailMessage.setText("Thank you for signing up with XYZ-service. To activate your account, please enter the following mock_user_id on the login screen. For security reasons, this link will only be valid for the next 24 hours.")

        when:
        emailService.sendEmail()

        then:
        1 * fakeUserService.getUser() >> mockUser

        and:
        1 * javaMailSender.send(simpleMailMessage)
    }
}
