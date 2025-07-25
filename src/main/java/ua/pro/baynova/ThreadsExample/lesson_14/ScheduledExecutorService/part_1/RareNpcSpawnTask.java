package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_1;

public class RareNpcSpawnTask implements Runnable {
    private final String npcName;

    public RareNpcSpawnTask(String npcName) {
        this.npcName = npcName;
    }

    @Override
    public void run() {
        System.out.println("🌟 Внимание! Появился редкий NPC: " + npcName + "!");
    }
}
