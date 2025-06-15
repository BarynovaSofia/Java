package ua.pro.baynova.TDD_Example.lesson_4;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<String> orders = new ArrayList<>();

    public void addOrder(String orderName){
        if(orderName == null){
            throw new IllegalArgumentException("Order name cannot be null");
        }
        orders.add(orderName);
    }

    public List<String> getLastOrders(int count){
        List<String> result = new ArrayList<>();
        int start = Math.max(orders.size() - count,0);
        for (int i = orders.size() - 1; i >= start ; i--) {
            result.add(orders.get(i));
        }
        return result;
    }

    public void clear(){
        orders.clear();
    }

    public boolean isEmpty(){
        return orders.isEmpty();
    }
}

