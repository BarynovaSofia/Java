package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

public class Order {
    private final int id;
    private final String customer;
    private final double amount;

    public Order(int id, String customer, double amount) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customer='" + customer + "', amount=" + amount + '}';
    }
}
