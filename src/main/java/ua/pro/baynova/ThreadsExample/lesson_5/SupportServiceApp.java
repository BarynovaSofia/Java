package ua.pro.baynova.ThreadsExample.lesson_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SupportServiceApp {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10 ; i++) {
            String clientName = "Клиент #" + i;
            int duration = 1 + (int) (Math.random() * 4);

            SupportCallTask call = new SupportCallTask(clientName, duration);
            executor.submit(call);
        }
        executor.shutdown();
        System.out.println("Все звонки переданы в службу. Ждём завершения...");

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("Некоторые звонки не завершились вовремя.");
            } else {
                System.out.println("Служба поддержки завершила работу.");
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
