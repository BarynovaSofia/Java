package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MdcLogbackApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            executor.submit(new MdcWorker(i));
        }
        executor.shutdownNow();
    }
}
