package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

public class OrderProcessor implements Runnable{
    private final OrderQueueManager queueManager;
    private final int ordersToProcess;

    public OrderProcessor(OrderQueueManager queueManager, int ordersToProcess){
        this.queueManager = queueManager;
        this.ordersToProcess = ordersToProcess;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < ordersToProcess; i++) {
                Order order = queueManager.takeOrder();
                System.out.println("Обрабатывается: " + order);
                Thread.sleep(150);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
