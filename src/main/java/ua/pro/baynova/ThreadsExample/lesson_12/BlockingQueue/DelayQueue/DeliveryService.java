package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.DelayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeliveryService {
    private final BlockingQueue<DeliveryPackage> queue = new DelayQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void start(){
        executor.submit(new PackageProducer(queue));
        executor.submit(new PackageConsumer(queue));
    }

    public void shutdown(){
        executor.shutdownNow();
    }

    public void awaitTermination(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
