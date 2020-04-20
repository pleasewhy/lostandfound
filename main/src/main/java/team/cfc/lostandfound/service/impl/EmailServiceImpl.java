package team.cfc.lostandfound.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.cfc.lostandfound.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Override
    public void sendEmail(String msg, String emailAddr, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailUsername);
        message.setTo(emailAddr);
        message.setSubject(subject);
        message.setText(msg);
        System.out.println(javaMailSender);
        javaMailSender.send(message);
    }
}
