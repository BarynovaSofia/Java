package ua.pro.baynova.MockitoExample.lesson_9;

public class StoreService {
    private final PaymentGateway gateway;

    public StoreService(PaymentGateway gateway){
        this.gateway = gateway;
    }

    public boolean processPayment(String card, double amount){
        for (int i = 0; i < 3; i++) {
            if (gateway.charge(card, amount)){
                return true;
            }
        }
        return false;
    }
}
