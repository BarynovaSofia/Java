package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Courier implements Runnable {
    private final BlockingQueue<String> queue;
    private final Logger logger;

    public Courier(BlockingQueue<String> queue, Logger logger){
        this.queue = queue;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (true){
                String bread = queue.take();
                logger.info("Забрали " + bread + " для доставки");

                Thread.sleep((int) (Math.random() * 2000));

                if (Math.random() < 0.2){
                    logger.warning("Опоздали с доставкой " + bread);
                } else {
                    logger.info("Доставили " + bread);
                }
            }
        } catch (InterruptedException e){
            logger.severe("Курьер прерван!");
            Thread.currentThread().interrupt();
        }
    }
}
