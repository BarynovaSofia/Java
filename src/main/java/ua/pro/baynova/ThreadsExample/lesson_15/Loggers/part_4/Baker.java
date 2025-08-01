package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Baker implements Runnable {
    private final BlockingQueue<String> queue;
    private final Logger logger;

    public Baker(BlockingQueue<String> queue, Logger logger){
        this.queue = queue;
        this.logger = logger;
    }

    @Override
    public void run() {
        int breadNumber = 1;

        try {
            while (breadNumber <= 5){
                Thread.sleep(1000);
                String bread = "Хлеб № " + breadNumber;
                queue.put(bread);
                logger.info("Испекли " + bread);
                breadNumber++;
            }
        } catch (InterruptedException e){
            logger.severe("Пекарь прерван!");
            Thread.currentThread().interrupt();
        }
    }
}
