package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.DelayQueue;

import java.util.concurrent.BlockingQueue;

public class PackageConsumer implements Runnable {
    private final BlockingQueue<DeliveryPackage> queue;

    public PackageConsumer(BlockingQueue<DeliveryPackage> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                DeliveryPackage deliveryPackage = queue.take();
                System.out.println("🚚 Доставлена " + deliveryPackage.getCustomerName());
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
