package ua.pro.baynova.ThreadsExample.lesson_13.FutureGet;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class EmailServiceAppMain {
    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
                "alice@example.com",
                "bob@example.com",
                "неправильный_адрес",
                "charlie@example.com"
        );

        EmailService service = new EmailService(Executors.newFixedThreadPool(2));
        service.sendEmails(emails);
        service.shutdown();
    }
}
