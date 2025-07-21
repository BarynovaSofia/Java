package ua.pro.baynova.ThreadsExample.lesson_13.FutureGet;

import java.util.concurrent.Callable;

public class EmailTask implements Callable<String> {
    private final String recipient;

    public EmailTask(String recipient){
        this.recipient = recipient;
    }

    @Override
    public String call() throws Exception {
        if (!recipient.contains("@")){
            throw new IllegalArgumentException("Некорректный email: " + recipient);
        }
        Thread.sleep(1000);

        return "Письмо успешно отправлено " + recipient;
    }
}
