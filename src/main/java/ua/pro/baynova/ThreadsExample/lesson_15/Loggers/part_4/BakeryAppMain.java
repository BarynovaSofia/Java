package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class BakeryAppMain {
    public static void main(String[] args) throws IOException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        Logger bakerLogger = LoggerSetup.createLogger(Baker.class);
        Logger courierLogger = LoggerSetup.createLogger(Courier.class);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Baker(blockingQueue, bakerLogger));
        executor.submit(new Courier(blockingQueue, courierLogger));

        executor.shutdown();
    }
}
