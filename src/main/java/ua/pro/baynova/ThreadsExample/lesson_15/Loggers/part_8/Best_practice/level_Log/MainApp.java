package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_8.Best_practice.level_Log;

public class MainApp {
    public static void main(String[] args) {
        OrderService service = new OrderService(3);
        service.startProcessing(15);
    }
}
