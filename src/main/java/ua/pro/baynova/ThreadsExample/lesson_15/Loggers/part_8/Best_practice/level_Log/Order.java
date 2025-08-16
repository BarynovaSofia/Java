package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_8.Best_practice.level_Log;

public class Order {
    private final String id;
    private final int itemCount;

    public Order(String id, int itemCount){
        this.id = id;
        this.itemCount = itemCount;
    }

    public String getId() {
        return id;
    }

    public int getItemCount() {
        return itemCount;
    }
}
