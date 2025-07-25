package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScannerService {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void startScanning(){
        System.out.println("🚀 Запускаем планировщик сканирования архива...");
        scheduler.scheduleWithFixedDelay(new MessageArchiveScanner(), 0, 5, TimeUnit.SECONDS);
    }

    public void stopScanning(){
        System.out.println("🛑 Останавливаем планировщик сканирования...");
        scheduler.shutdown();
    }
}
