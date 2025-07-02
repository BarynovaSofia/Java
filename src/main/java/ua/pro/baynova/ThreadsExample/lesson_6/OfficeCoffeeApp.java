package ua.pro.baynova.ThreadsExample.lesson_6;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OfficeCoffeeApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicInteger coffeeCounter = new AtomicInteger(0);

        executor.submit(new CoffeeRunnerTask("Саша", coffeeCounter));
        executor.submit(new CoffeeRunnerTask("Лена", coffeeCounter));
        executor.submit(new CoffeeRunnerTask("Дима", coffeeCounter));

        Future<String> result1 = executor.submit(new CoffeePriceFetcherTask("Юля"));
        Future<String> result2 = executor.submit(new CoffeePriceFetcherTask("Артём"));

        System.out.println("Ждём результаты от тех, кто ходил узнавать цену...");
        System.out.println(result1.get());
        System.out.println(result2.get());

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Всего людей вернулись с кофе: " + coffeeCounter.get());
    }
}
