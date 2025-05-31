package ua.pro.baynova.MockitoExample.lesson_7;

public class EmailService {

    private final EmailClient emailClient;

    public EmailService(EmailClient emailClient){
        this.emailClient = emailClient;
    }

    public boolean notifyClient(String email, String message){
        try {
            emailClient.sendEmail(email, message);
            return true;
        } catch (RuntimeException e){
            System.out.println("Ошибка при отправке письма: " + e.getMessage());
            return false;
        }
    }
}
