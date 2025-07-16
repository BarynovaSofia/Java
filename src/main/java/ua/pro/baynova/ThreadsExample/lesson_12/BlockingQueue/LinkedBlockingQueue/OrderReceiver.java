package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

public class OrderReceiver implements Runnable{
    private final OrderQueueManager queueManager;
    private final int totalOrders;
    private final String customerNamePrefix;

    public OrderReceiver(OrderQueueManager queueManager, int totalOrders, String customerNamePrefix){
        this.queueManager = queueManager;
        this.totalOrders = totalOrders;
        this.customerNamePrefix = customerNamePrefix;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalOrders; i++) {
                Order order = new Order(i, customerNamePrefix + "_Customer" + i);
                queueManager.putOrder(order);
                Thread.sleep(200);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
