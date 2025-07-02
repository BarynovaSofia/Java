package ua.pro.baynova.ThreadsExample.lesson_6;

import java.util.concurrent.atomic.AtomicInteger;

public class CoffeeRunnerTask implements Runnable {
    private final String name;
    private final AtomicInteger count;

    public CoffeeRunnerTask(String name, AtomicInteger count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(name + " пошёл за кофе...");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " вернулся с кофе");
        count.incrementAndGet();
    }
}
