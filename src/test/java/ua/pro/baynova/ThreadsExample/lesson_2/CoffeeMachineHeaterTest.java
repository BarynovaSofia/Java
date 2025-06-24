package ua.pro.baynova.ThreadsExample.lesson_2;

import org.junit.jupiter.api.Test;

public class CoffeeMachineHeaterTest {

    @Test
    void shouldHandleInterruption() throws InterruptedException {
        Thread thread = new Thread(new CoffeeMachineHeater());

        thread.start();
        thread.interrupt();
        thread.join();
    }
}
