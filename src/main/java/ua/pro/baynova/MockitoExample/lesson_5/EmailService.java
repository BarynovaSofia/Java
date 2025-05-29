package ua.pro.baynova.MockitoExample.lesson_5;

public class EmailService {
    private final MailSender mailSender;

    public EmailService(MailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String email){
        String trimmedEmail = email.trim();
        String message = "Welcome to our platform!";

        mailSender.send(trimmedEmail, message);
    }
}
