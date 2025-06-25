package ua.pro.baynova.ThreadsExample.lesson_3;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeCounterTest {

    @RepeatedTest(5)
    void counterShouldBeExactly2000WithoutSynchronized() throws InterruptedException{
        CoffeeCounter coffeeCounter = new CoffeeCounter();

        Thread barista1 = new Thread(new BaristaTask(coffeeCounter));
        Thread barista2 = new Thread(new BaristaTask(coffeeCounter));

        barista1.start();
        barista2.start();

        barista1.join();
        barista2.join();

        int finalCount = coffeeCounter.getCounter();
        System.out.println("Результат: " + finalCount);

        assertEquals(2000, finalCount);
    }
}
