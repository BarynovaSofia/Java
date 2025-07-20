package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.DelayQueue;

public class DeliveryServiceAppMain {
    public static void main(String[] args) {
        DeliveryService service = new DeliveryService();
        service.start();

        service.awaitTermination(8000);
        service.shutdown();

        System.out.println("✅ Работа службы доставки завершена");
    }
}
