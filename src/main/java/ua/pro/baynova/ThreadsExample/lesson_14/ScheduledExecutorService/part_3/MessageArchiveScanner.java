package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_3;

import java.util.Random;

public class MessageArchiveScanner implements Runnable {
    private final Random random = new Random();
    private int scanCount;

    @Override
    public void run() {
        scanCount++;
        System.out.println("🔍 Запуск сканирования архива. Номер сканирования: ");

        try {
            int scanTime = 2000 + random.nextInt(4000);
            Thread.sleep(scanTime);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("⏹️ Сканирование прервано");
            return;
        }
        System.out.println("✅ Сканирование архива #" + scanCount + " завершено");
    }
}
