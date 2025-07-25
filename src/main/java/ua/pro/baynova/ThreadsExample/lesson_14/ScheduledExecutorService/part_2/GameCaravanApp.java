package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_2;

public class GameCaravanApp {
    public static void main(String[] args) {
        CaravanScheduler scheduler = new CaravanScheduler();
        scheduler.startCaravanSchedule(2, 3);

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        scheduler.shutdown();
    }
}
