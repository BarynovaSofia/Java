package ua.pro.baynova.ThreadsExample.lesson_12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BasketApp {
    public void start(int iterations) throws InterruptedException{
        Basket basket = new Basket();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Consumer(basket, iterations));
        Thread.sleep(100);
        executor.submit(new Producer(basket, iterations));

        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("Производитель или потребитель не успели завершиться вовремя.");
            executor.shutdownNow();
        }
    }
}
