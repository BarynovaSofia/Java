package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmergencyApp {
    public void start() throws InterruptedException{
        CallQueueManager queueManager = new PriorityCallQueueManagerImpl();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new EmergencyDispatcher(queueManager, 10));

        executor.submit(new CallOperator(queueManager, "Operator-1", 5));
        executor.submit(new CallOperator(queueManager, "Operator-2", 5));

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("⚠️ Потоки не завершились вовремя. Завершаю вручную.");
            executor.shutdownNow();
        }
        System.out.println("✅ Все вызовы обработаны.");
    }
}
