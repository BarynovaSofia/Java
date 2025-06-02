package ua.pro.baynova.MockitoExample.lesson_9;

public interface PaymentGateway {
    boolean charge(String cardNumber, double amount);
}
