package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderApp {
    public void start() throws InterruptedException{
        OrderQueueManager queueManager = new LinkedOrderQueueManagerImpl();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new OrderReceiver(queueManager, 5, "Alice"));
        executor.submit(new OrderReceiver(queueManager, 5, "Bob"));

        executor.submit(new OrderProcessor(queueManager, 5));
        executor.submit(new OrderProcessor(queueManager, 5));

        executor.shutdown();

        if (!executor.awaitTermination(10, TimeUnit.SECONDS)){
            System.out.println("Потоки не завершились вовремя!");
            executor.shutdownNow();
        } else {
            System.out.println("Обработка заказов завершена.");
        }
    }
}
