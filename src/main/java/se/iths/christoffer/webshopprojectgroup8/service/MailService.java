package se.iths.christoffer.webshopprojectgroup8.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.iths.christoffer.webshopprojectgroup8.model.Order;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderEmail(String to, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Order Confirmation");

        message.setText(
                "Thank you for your order!\n\n" +
                        "Total: " + order.getTotalPrice()
        );

        mailSender.send(message);
    }
}

