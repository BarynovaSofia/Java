package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.SynchronousQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Courier implements Runnable {
    private final BlockingQueue<Package> queue;

    public Courier(SynchronousQueue<Package> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                Package deliveryPackage = new Package(String.valueOf(i), "Товар-" + i);
                System.out.println("\uD83D\uDEB6 Курьер принёс: " + deliveryPackage);
                queue.put(deliveryPackage);
                System.out.println("✅ Посылка #" + i + " передана водителю");
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
