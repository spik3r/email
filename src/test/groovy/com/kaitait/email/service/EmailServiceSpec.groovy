package com.kaitait.email.service

import com.kaitait.email.domain.EmailedUser
import com.kaitait.email.domain.User
import com.kaitait.email.repository.EmailedUserRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification

import static com.kaitait.email.service.EmailService.FROM_EMAIL

class EmailServiceSpec extends Specification{

    JavaMailSender javaMailSender
    EmailService emailService
    FakeUserService fakeUserService
    EmailedUserRepository emailedUserRepository
    User mockUser

    def setup() {
        javaMailSender = Mock()
        fakeUserService = Mock()
        emailedUserRepository = Mock()
        mockUser = new User("John", "Smith", "js@gmail.com","pochahontas123")
        emailService = new EmailService(javaMailSender, fakeUserService, emailedUserRepository)
    }

    def "sendEmail works as expected when no User is passed in"() {
        given:
        EmailedUser user = new EmailedUser(mockUser)
//
//        and:
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage()
//        simpleMailMessage.setFrom(FROM_EMAIL)
//        simpleMailMessage.setReplyTo(FROM_EMAIL)
//        simpleMailMessage.setTo(mockUser.getEmail())
//        simpleMailMessage.setSubject("Hello: John! Welcome to XYZ-service")
//        simpleMailMessage.setText("Thank you for signing up with XYZ-service. To activate your account, please enter the following mock_user_id on the login screen. For security reasons, this link will only be valid for the next 24 hours.")

        when:
        emailService.registerNewUser(mockUser)

        then:
        1 * emailedUserRepository.save(_) >> user

        and:
        1 * javaMailSender.send(_)
    }
}
