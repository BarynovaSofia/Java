package ua.pro.baynova.ThreadsExample.lesson_5.Consolecapture;

import java.util.List;

public class OrderPrinter {
    public void printOrders(List<String> orders){
        System.out.println("====== Заказы клиентов ======");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, orders.get(i) );
        }
        System.out.println("========= Конец списка =========");
    }
}
