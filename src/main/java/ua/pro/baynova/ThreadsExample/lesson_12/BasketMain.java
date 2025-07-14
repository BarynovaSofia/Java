package ua.pro.baynova.ThreadsExample.lesson_12;

public class BasketMain {
    public static void main(String[] args) throws InterruptedException {
        BasketApp app = new BasketApp();
        app.start(10);
        System.out.println("Работа завершена");
    }
}
