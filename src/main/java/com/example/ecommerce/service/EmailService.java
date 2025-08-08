package com.example.ecommerce.service;

import org.springframework.mail.SimpleMailMessage;

import static java.lang.Long.parseLong;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("spoc.lnctgroup.springboard@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
