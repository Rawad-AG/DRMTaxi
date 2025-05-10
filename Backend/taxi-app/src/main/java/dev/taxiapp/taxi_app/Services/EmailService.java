package dev.taxiapp.taxi_app.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.taxiapp.taxi_app.Configs.PropertiesProvider;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String FROM;

    public EmailService(JavaMailSender mailSender, PropertiesProvider provider) {
        this.mailSender = mailSender;
        this.FROM = provider.email();
    }


    @Async
    public void send(String to, String subject, String message) throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(FROM);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    @Async
    public void sendVerifyToken(String to, String token, Long id) throws MessagingException {
        try {
            String verifyLink = "http://localhost:8080/web/v1/auth/verifyemail?token=" + token +"&id=" + id;

            String message = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; padding: 50px; }" +
                ".container { background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); max-width: 500px; margin: auto; }" +
                "h2 { color: #333333; }" +
                "p { color: #666666; font-size: 16px; }" +
                ".button { display: inline-block; padding: 10px 20px; font-size: 18px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px; margin-top: 20px; }" +
                ".footer { margin-top: 20px; font-size: 14px; color: #999999; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h2>Email Verification</h2>" +
                "<p>Thank you for signing up! Please verify your email by clicking the button below:</p>" +
                "<a href='" + verifyLink + "' class='button'>Verify Email</a>" +
                "<p class='footer'>If you did not request this, please ignore this email.</p>" +
                "</div>" +
                "</body>" +
                "</html>";

            
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setSubject("verify your email");
            helper.setFrom(FROM);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


}
