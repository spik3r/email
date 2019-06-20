package com.kaitait.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.util.Date;

@Slf4j
@Service
public class EmailService {

    private static final String FROM_EMAIL = "no_reply@xyz-service.com";
    private static final String ACCOUNT_ACTIVATION_EMAIL = "Thank you for signing up with XYZ-service." +
            " To activate your account, please enter the following %s on the login screen. For security reasons, this link will only be valid for the next 24 hours.";
    private JavaMailSender javaMailSender;

    @Inject
    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(final User user) throws MailException, MessagingException {

        log.info("sendMail called with User: {}", user);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(FROM_EMAIL);
        mail.setReplyTo(FROM_EMAIL);
        mail.setTo(user.getEmail());
        mail.setSubject(String.format("Hello: %s! Welcome to XYZ-service", user.getFirstName()));
        mail.setText(String.format(ACCOUNT_ACTIVATION_EMAIL, user.getId()));

        log.info("sending mail with text {}", mail.getText());
        javaMailSender.send(mail);

//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
//        String htmlMsg = "<h3>Hello World!</h3>";
//        mimeMessage.setContent(htmlMsg, "text/html");
//        helper.setTo("someone@abc.com");
//        helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
//        helper.setFrom("abc@gmail.com");
//        javaMailSender.send(mimeMessage);
    }

    void sendEmail() throws MessagingException {
        final User user = new User();
        user.setEmail("dummy@test.com");
        user.setFirstName("AAA");
        user.setLastName("BBB");
        user.setPassword("abc123");
        user.setId("123");
        user.setCreatedAt(new Date());

        log.info("sendMail called without user creating dummy", user);
        sendEmail(user);
    }

}
