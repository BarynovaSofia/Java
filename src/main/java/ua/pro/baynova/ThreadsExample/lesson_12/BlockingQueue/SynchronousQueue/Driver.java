package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.SynchronousQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Driver implements Runnable {
    private final BlockingQueue<Package> queue;

    public Driver(SynchronousQueue<Package> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                Package deliveryPackage = queue.take();
                System.out.println("🚚 Водитель получил: " + deliveryPackage);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
