package com.kaitait.email.service;

import com.kaitait.email.domain.EmailedUser;
import com.kaitait.email.domain.User;
import com.kaitait.email.repository.EmailedUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class EmailService {

    public static final String FROM_EMAIL = "no_reply@xyz-service.com";
    public static final String ACCOUNT_ACTIVATION_EMAIL = "Thank you for signing up with XYZ-service." +
            " To activate your account, please enter the following %s on the login screen. For security reasons, this link will only be valid for the next 24 hours.";

    private JavaMailSender javaMailSender;
    private FakeUserService fakeUserService;
    private EmailedUserRepository emailedUserRepository;


    @Inject
    public EmailService(final JavaMailSender javaMailSender, final FakeUserService fakeUserService, EmailedUserRepository emailedUserRepository) {
        this.javaMailSender = javaMailSender;
        this.fakeUserService = fakeUserService;
        this.emailedUserRepository = emailedUserRepository;
    }

    public void registerNewUser(final User user) {
        log.info("registerNewUser called with User: {}", user);
        final EmailedUser emailedUser = emailedUserRepository.save(new EmailedUser(user));
        sendEmail(emailedUser);
    }

    private void sendEmail(final EmailedUser user) throws MailException {
        log.info("sendMail called with User: {}", user);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(FROM_EMAIL);
        mail.setReplyTo(FROM_EMAIL);
        mail.setTo(user.getEmail());
        mail.setSubject(String.format("Hello: %s! Welcome to XYZ-service", user.getFirstName()));
        mail.setText(String.format(ACCOUNT_ACTIVATION_EMAIL, user.getId()));

        log.info("sending mail with text {}", mail.getText());
        javaMailSender.send(mail);
    }

    public void sendEmail() {
        final User user = fakeUserService.getUser();
        log.info("sendMail called without user creating dummy", user);
        registerNewUser(user);
    }


}
