package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        try {
            LoggerSetup.setup();
        } catch (IOException e){
            System.out.println("Ошибка при настройке логгера: " + e.getMessage());
            return;
        }

        RequestProcessingService processingService = new RequestProcessingService();
        processingService.process();
    }
}
