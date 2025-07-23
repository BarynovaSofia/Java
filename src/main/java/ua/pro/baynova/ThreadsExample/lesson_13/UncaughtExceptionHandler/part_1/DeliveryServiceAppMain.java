package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_1;

public class DeliveryServiceAppMain {
    public static void main(String[] args) {
       DeliveryWorker worker = new DeliveryWorker();

        worker.deliver("Посылка №1");
        worker.deliver("Посылка №2");
        worker.deliver("Посылка №3"); //упадёт
        worker.deliver("Посылка №4");
    }
}
