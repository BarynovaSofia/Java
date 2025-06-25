package ua.pro.baynova.ThreadsExample.lesson_3;

public class BaristaTaskAtomic implements Runnable{
    private final CoffeeCounterAtomic coffeeCounterAtomic;

    public BaristaTaskAtomic (CoffeeCounterAtomic coffeeCounter){
        this.coffeeCounterAtomic = coffeeCounter;
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            coffeeCounterAtomic.increment();
        }
    }
}
