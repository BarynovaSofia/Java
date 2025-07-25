package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CaravanScheduler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void startCaravanSchedule(int initialDelaySec, int periodSec){
        Runnable task = new CaravanSpawnTask();
        System.out.println("📅 Караваны начнут прибывать через " + initialDelaySec +
                " секунд и будут приходить каждые " + periodSec + " секунд.");

        scheduler.scheduleAtFixedRate(task, initialDelaySec, periodSec, TimeUnit.SECONDS);
    }

    public void shutdown(){
        scheduler.shutdown();
    }
}
