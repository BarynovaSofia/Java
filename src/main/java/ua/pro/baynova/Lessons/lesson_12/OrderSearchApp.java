package ua.pro.baynova.Lessons.lesson_12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderSearchApp {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(101, "Anna"));
        orders.add(new Order(102, "Sven"));
        orders.add(new Order(103, "Cris"));
        orders.add(new Order(104, "Sasha"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your order number: ");
        int searchId = scanner.nextInt();

        boolean found = false;

        for (Order order : orders){
            if (order.id == searchId){
                System.out.println("Order found! Customer: " + order.customerName);
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("No order with this number was found.");
        }
    }
}

