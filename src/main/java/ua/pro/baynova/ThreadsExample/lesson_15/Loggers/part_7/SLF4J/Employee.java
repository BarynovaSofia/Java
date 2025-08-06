package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.SLF4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Employee implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    private final String name;
    private final BlockingQueue<PrintJob> queue;
    private final Random random = new Random();

    public Employee(String name, BlockingQueue<PrintJob> queue){
        this.name = name;
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                String docName = "Report_" + random.nextInt(1000);
                int pages = random.nextInt(10) + 1;

                PrintJob job = new PrintJob(docName, name, pages);

                queue.put(job);
                logger.info("{} отправил на печать: {}", name, job);

                Thread.sleep(random.nextInt(2000) + 1000);
            }
        } catch (InterruptedException e){
            logger.warn("{} завершает работу (прерван)" , name);
            Thread.currentThread().isInterrupted();
        } catch (Exception e){
            logger.error("Ошибка у сотрудника {}: {}", name, e.getMessage(), e);
        }
    }
}
