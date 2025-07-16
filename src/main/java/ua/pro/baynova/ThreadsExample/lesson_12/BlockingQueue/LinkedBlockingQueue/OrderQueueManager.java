package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

public interface OrderQueueManager {
    void putOrder(Order order) throws InterruptedException;
    Order takeOrder() throws InterruptedException;
}

