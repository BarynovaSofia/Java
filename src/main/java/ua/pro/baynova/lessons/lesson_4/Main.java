package ua.pro.baynova.lessons.lesson_4;

public class Main {
    public static void main(String[] args) {
        StoreQueue store = new StoreQueue();

        store.addCustomer("Саша");
        store.addCustomer("Аня");
        store.addCustomer("Боря");

        System.out.println("Первый в очереди: " + store.peekCustomer());

        store.serveCustomer();

        System.out.println("Теперь первый в очереди: " + store.peekCustomer());

        store.serveCustomer();
        store.serveCustomer();

        System.out.println("Очередь пуста? " + store.isEmpty());

    }
}
