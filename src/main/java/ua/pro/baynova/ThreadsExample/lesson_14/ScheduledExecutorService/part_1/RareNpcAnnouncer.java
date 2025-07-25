package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RareNpcAnnouncer {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void announceNpcSpawn(String npcName, int delaySeconds) {
        Runnable task = new RareNpcSpawnTask(npcName);
        System.out.println("🕒 NPC " + npcName + " появится через " + delaySeconds + " секунд...");
        scheduler.schedule(task, delaySeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
