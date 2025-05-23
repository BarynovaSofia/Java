package ua.pro.baynova.MockitoExample.lesson_1;

import java.util.List;

public class SmartFridge {

    private List<String> products;
    private OnlineStore store;

    public SmartFridge(List<String> products, OnlineStore store){
        this.products = products;
        this.store = store;
    }

    public boolean checkAndOrder(String productName){
        if (products.contains(productName)){
            return true;
        }
        return store.orderProduct(productName);
    }
}
