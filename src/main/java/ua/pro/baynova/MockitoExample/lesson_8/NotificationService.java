package ua.pro.baynova.MockitoExample.lesson_8;

public class NotificationService {

    public void sendEmail(String email, String message) {
        System.out.println(" " + email + ": " + message);
    }

    public void sendSms(String phone, String message) {
        System.out.println(" " + phone + ": " + message);
    }

    public void sendAll(String email, String phone, String message){
        sendEmail(email, message);
        sendSms(phone, message);
    }
}
