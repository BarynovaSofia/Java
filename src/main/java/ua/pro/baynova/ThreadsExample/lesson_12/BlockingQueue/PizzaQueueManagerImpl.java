package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PizzaQueueManagerImpl implements PizzaQueueManager{
    private final BlockingQueue<Pizza> queue;

    public PizzaQueueManagerImpl(int capacity){
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void putPizza(Pizza pizza) throws InterruptedException {
        queue.put(pizza);
    }

    @Override
    public Pizza takePizza() throws InterruptedException {
        return queue.take();
    }
}
