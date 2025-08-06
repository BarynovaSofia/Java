package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.SLF4J;

public class OfficePrintSystem {
    public static void main(String[] args) throws InterruptedException {
        OfficePrintManager manager = new OfficePrintManager();

        manager.start();
        Thread.sleep(15000);
        manager.stop();
    }
}
