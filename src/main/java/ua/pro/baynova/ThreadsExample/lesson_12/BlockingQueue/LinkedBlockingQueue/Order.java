package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.LinkedBlockingQueue;

public class Order {
    private final int id;
    private final String customer;

    public Order(int id, String customer){
        this.id = id;
        this.customer = customer;
    }

    public int getId(){
        return id;
    }

    public String getCustomer(){
        return customer;
    }

    @Override
    public String toString(){
        return "Order #" + id + " for " + customer;
    }
}
