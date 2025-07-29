package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CandyFactoryMain {
    public static void main(String[] args) {
        SimpleLogger logger = new SimpleLogger(SimpleLogger.Level.DEBUG);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new CandyWorker("Света", logger));
        executor.submit(new CandyWorker("Олег", logger));
        executor.submit(new CandyWorker("Марина", logger));

        executor.shutdown();
    }
}
