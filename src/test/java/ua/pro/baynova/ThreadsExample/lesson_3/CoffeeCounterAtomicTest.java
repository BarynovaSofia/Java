package ua.pro.baynova.ThreadsExample.lesson_3;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeCounterAtomicTest {

    @RepeatedTest(5)
    void shouldHandle10ThreadsCorrectly() throws InterruptedException {
        CoffeeCounterAtomic counter = new CoffeeCounterAtomic();

        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new BaristaTaskAtomic(counter));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = counter.getCounter();
        System.out.println("Результат с 10 потоками: " + result);

        assertEquals(10000, result);
    }
}