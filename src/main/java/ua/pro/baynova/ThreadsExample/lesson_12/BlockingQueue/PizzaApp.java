package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PizzaApp {
    public void start() throws InterruptedException {
        PizzaQueueManager queue = new PizzaQueueManagerImpl(5);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new PizzaChef(queue, 5, "Маргарита", 1));
        executor.submit(new PizzaChef(queue, 5, "Пепперони", 2));

        executor.submit(new PizzaCourier(queue, 5, 1));
        executor.submit(new PizzaCourier(queue, 5, 2));

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Работа потоков не завершилась вовремя.");
            executor.shutdownNow();
        }
    }
}
