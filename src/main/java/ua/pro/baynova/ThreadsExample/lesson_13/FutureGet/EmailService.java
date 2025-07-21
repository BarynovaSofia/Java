package ua.pro.baynova.ThreadsExample.lesson_13.FutureGet;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class EmailService {
    private final ExecutorService executor;

    public EmailService(ExecutorService executor){
        this.executor = executor;
    }

    public void sendEmails(List<String> emails) {
        List<Future<String>> futures = emails.stream()
                .map(email -> executor.submit(new EmailTask(email)))
                .toList();

        for (int i = 0; i < futures.size(); i++) {
            try {
                String result = futures.get(i).get();
                System.out.println("✅ " + result);
            } catch (ExecutionException e) {
                System.err.println("❌ Ошибка при отправке письма: " + emails.get(i));
                System.err.println("   Причина: " + e.getCause().getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Поток был прерван");
            }
        }
    }

    public void shutdown(){
        executor.shutdown();
    }
}
