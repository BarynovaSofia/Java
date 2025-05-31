package ua.pro.baynova.MockitoExample.lesson_7;

public class ATM {

    private final BankService bankService;

    public ATM(BankService bankService){
        this.bankService = bankService;
    }

    public String makeWithdrawal(String cardNumber, double amount){
        try {
            bankService.withdraw(cardNumber, amount);
            return "Операция выполнена успешно";
        } catch (RuntimeException e){
            return "Ошибка: " + e.getMessage();
        }
    }
}
