package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.SLF4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Printer implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(Printer.class);

    private final BlockingQueue<PrintJob> queue;

    public Printer(BlockingQueue<PrintJob> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                PrintJob job = queue.take();
                logger.info("Печатаем: {}" + job);

                Thread.sleep(job.getPageCount() * 500L);
                logger.info("Задание выполнено: {}" + job);
            }
        } catch (InterruptedException e){
            logger.warn("Принтер завершает работу (прерван)");
            Thread.currentThread().isInterrupted();
        } catch (Exception e){
            logger.error("Ошибка принтера: {}", e.getMessage(), e);
        }
    }
}
