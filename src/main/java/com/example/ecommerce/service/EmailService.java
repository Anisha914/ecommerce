package com.example.ecommerce.service;

@Service
public class EmailService<SimpleMailMessage> {

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

