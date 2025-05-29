package ua.pro.baynova.MockitoExample.lesson_6;

public class CurrencyService {

    private final CurrencyApi currencyApi;

    public CurrencyService(CurrencyApi currencyApi){
        this.currencyApi = currencyApi;
    }

    public double convertEurToUsd(double eurAmount) {
        double rate = currencyApi.getExchangeRate("EUR", "USD");
        return eurAmount * rate;
    }
}
