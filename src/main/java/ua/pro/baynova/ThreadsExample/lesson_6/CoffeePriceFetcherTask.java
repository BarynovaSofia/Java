package ua.pro.baynova.ThreadsExample.lesson_6;

import java.util.concurrent.Callable;

public class CoffeePriceFetcherTask implements Callable<String> {
    private final String requester;

    public CoffeePriceFetcherTask(String requester){
        this.requester = requester;
    }

    @Override
    public String call() throws Exception{
        System.out.println(requester + " пошёл узнать цену кофе...");
        Thread.sleep(1500L);
        int price = 20 + (int) (Math.random() * 10);
        return requester + " узнал цену: " + price + " грн";
    }
}
