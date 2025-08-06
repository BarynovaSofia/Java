package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.SLF4J;

import java.util.concurrent.*;

public class OfficePrintManager {
    private final BlockingQueue<PrintJob> queue = new ArrayBlockingQueue<>(10);
    private final Employee emp1 = new Employee("Иван", queue);
    private final Employee emp2 = new Employee("Ольга", queue);
    private final Printer printer = new Printer(queue);

    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void start(){
        executor.submit(emp1);
        executor.submit(emp2);
        executor.submit(printer);
    }

    public void stop() throws InterruptedException{
        executor.shutdownNow();

        if (!executor.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("Потоки не завершились вовремя, принудительно завершаем");
        } else {
            System.out.println("Система печати завершена корректно.");
        }
    }
}
