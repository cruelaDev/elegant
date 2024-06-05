package org.example.elegant.security.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.example.elegant.security.email.Variables.*;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public void sendVerificationCode(String email, int code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_EMAIL);
            message.setTo(email);
            message.setSubject(VERIFY_CODE_MESSAGE);
            message.setText(String.valueOf(code));
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    todo  forget password

}
