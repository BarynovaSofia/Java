package ua.pro.baynova.JUnitExamle;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OrderProcessor {

    public List<String> sortOrders (List<String> orders){
        Collections.sort(orders);
        return orders;
    }
}
