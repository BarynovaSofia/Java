package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedOrderQueueManagerImpl implements OrderQueueManager{
    private final BlockingQueue<Order> queue;

    public LinkedOrderQueueManagerImpl(){
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void putOrder(Order order) throws InterruptedException {
        queue.put(order);
    }

    @Override
    public Order takeOrder() throws InterruptedException {
        return queue.take();
    }
}
