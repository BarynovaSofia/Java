package ua.pro.baynova.ThreadsExample.lesson_3;

import java.util.concurrent.atomic.AtomicInteger;

public class CoffeeCounterAtomic {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment(){
        counter.incrementAndGet();
    }

    public int getCounter(){
        return counter.get();
    }
} 
