package ua.pro.baynova.ThreadsExample.lesson_4;

public class WorkerWithoutVolatile {

    private volatile boolean running = true;

    public void stopWork(){
        running = false;
    }

    public void doWork(){
        while (running){
            System.out.println("Работаю...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Остановился...");
    }
}
