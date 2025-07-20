package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.DelayQueue;

import java.util.concurrent.BlockingQueue;

public class PackageProducer implements Runnable {
    private final BlockingQueue<DeliveryPackage> queue;

    public PackageProducer(BlockingQueue<DeliveryPackage> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new DeliveryPackage("Alice", 5));
            System.out.println("📦 Заказ от Alice принят (доставка через 5 сек)");

            queue.put(new DeliveryPackage("Bob", 3));
            System.out.println("📦 Заказ от Bob принят (доставка через 3 сек)");

            queue.put(new DeliveryPackage("Charlie", 1));
            System.out.println("📦 Заказ от Charlie принят (доставка через 1 сек)");

        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
