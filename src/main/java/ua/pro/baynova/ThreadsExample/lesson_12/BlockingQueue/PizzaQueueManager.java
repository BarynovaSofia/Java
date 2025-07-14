package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

public interface PizzaQueueManager {
    void putPizza(Pizza pizza) throws InterruptedException;
    Pizza takePizza() throws InterruptedException;
}
