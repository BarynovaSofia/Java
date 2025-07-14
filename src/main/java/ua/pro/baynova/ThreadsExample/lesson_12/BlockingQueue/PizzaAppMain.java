package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

public class PizzaAppMain {
    public static void main(String[] args) throws InterruptedException {
        new PizzaApp().start();
        System.out.println("Фабрика завершила работу!");
    }
}
