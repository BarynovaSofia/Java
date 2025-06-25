package ua.pro.baynova.ThreadsExample.lesson_3;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeCounterAtomicTest {

    @RepeatedTest(5)
    void counterShouldBeExactly2000WithAtomicInteger() throws InterruptedException{
        CoffeeCounterAtomic coffeeCounterAtomic = new CoffeeCounterAtomic();

        Thread barista1 = new Thread(new BaristaTaskAtomic(coffeeCounterAtomic));
        Thread barista2 = new Thread(new BaristaTaskAtomic(coffeeCounterAtomic));

        barista1.start();
        barista2.start();

        barista1.join();
        barista2.join();

        int result = coffeeCounterAtomic.getCounter();
        System.out.println("Результат (Atomic): " + result);
        assertEquals(2000, result);
    }

}