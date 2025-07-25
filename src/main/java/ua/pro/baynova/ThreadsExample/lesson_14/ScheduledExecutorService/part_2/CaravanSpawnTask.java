package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_2;

public class CaravanSpawnTask implements Runnable {
    private int counter = 1;

    @Override
    public void run() {
        System.out.println("🚚 Караван #" + counter++ + " прибыл в город!");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
