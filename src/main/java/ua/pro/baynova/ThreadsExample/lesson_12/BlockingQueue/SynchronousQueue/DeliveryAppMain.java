package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.SynchronousQueue;

public class DeliveryAppMain {
    public static void main(String[] args) {
        DeliveryManager manager = new DeliveryManager();
        manager.start();

        manager.awaitTermination(6000);
        manager.shutdown();

        System.out.println("🏁 Работа доставки завершена");
    }
}
