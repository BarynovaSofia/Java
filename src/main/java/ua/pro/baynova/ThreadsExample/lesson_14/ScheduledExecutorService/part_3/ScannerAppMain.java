package ua.pro.baynova.ThreadsExample.lesson_14.ScheduledExecutorService.part_3;

public class ScannerAppMain {
    public static void main(String[] args) throws InterruptedException {
        ScannerService service = new ScannerService();
        service.startScanning();

        Thread.sleep(20000);

        service.stopScanning();
        System.out.println("✅ Работа планировщика завершена.");
    }
}
