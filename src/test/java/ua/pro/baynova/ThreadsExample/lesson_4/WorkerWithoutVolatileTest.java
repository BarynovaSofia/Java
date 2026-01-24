package ua.pro.baynova.ThreadsExample.lesson_4;

import org.junit.jupiter.api.Test;

class WorkerWithoutVolatileTest {

    @Test
    void workerShouldStopButMayNot() throws InterruptedException{
        WorkerWithoutVolatile worker = new WorkerWithoutVolatile();

        Thread workerThread = new Thread(worker::doWork);
        workerThread.start();

        Thread.sleep(2000);
        worker.stopWork();

        Thread.sleep(1000);
        System.out.println("Тест завершён.");
    }
}
