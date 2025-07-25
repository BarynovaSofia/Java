package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_1;

public class GameMainApp {
    public static void main(String[] args) {
        RareNpcAnnouncer announcer = new RareNpcAnnouncer();
        announcer.announceNpcSpawn("Древний Дракон", 5);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        announcer.shutdown();
    }
}
