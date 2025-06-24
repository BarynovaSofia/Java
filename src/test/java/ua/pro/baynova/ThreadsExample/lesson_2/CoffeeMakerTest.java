package ua.pro.baynova.ThreadsExample.lesson_2;

import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {

    @Test
    void shouldHandleInterruption() throws InterruptedException{
        Thread thread = new Thread(new CoffeeMaker());

        thread.start();
        thread.interrupt();
        thread.join();
    }
}
