package ua.pro.baynova.ThreadsExample.lesson_2;

import org.junit.jupiter.api.Test;

public class BeansGrinderTest {

    @Test
    void shouldHandleInterruption() throws InterruptedException{
        Thread thread = new Thread(new BeansGrinder());

        thread.start();
        thread.interrupt();
        thread.join();
    }
}
