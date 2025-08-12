package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.Log4j2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5 ; i++) {
            final int requestId = i;
            executor.submit(() -> {
                RequestProcessor processor = new RequestProcessor();
                processor.processRequest("REQ-" + requestId, "USER-" + (100 + requestId));
            });
        }
        executor.shutdown();
    }
}
