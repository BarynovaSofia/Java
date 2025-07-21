package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.SynchronousQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class DeliveryManager {
    private final SynchronousQueue<Package> queue = new SynchronousQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void start(){
        executor.submit(new Courier(queue));
        executor.submit(new Driver(queue));
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
