package ua.pro.baynova.ThreadsExample.lesson_10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DungeonAppLogic {
    public void runSimulation() throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(3);
        DungeonWithLock dungeon = new DungeonWithLock();

        executor.submit(() -> dungeon.enterAsNPC("НПС-1"));

        Thread.sleep(300);

        executor.submit(() -> dungeon.enterAsPlayer("Игрок-1"));
        executor.submit(() -> dungeon.enterAsPlayer("Игрок-2"));
        executor.submit(() -> dungeon.enterAsPlayer("Игрок-3"));

        Thread.sleep(2000);

        executor.submit(() -> dungeon.enterAsNPC("НПС-2"));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
