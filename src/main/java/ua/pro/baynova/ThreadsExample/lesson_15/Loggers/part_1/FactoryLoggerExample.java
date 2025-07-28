package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FactoryLoggerExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Worker("Света"));
        executor.submit(new Worker("Олег"));
        executor.submit(new Worker("Марина"));

        executor.shutdown();
    }
}
