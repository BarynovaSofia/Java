package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

public class Order {
    private final int id;
    private final String content;

    public Order(int id, String content){
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
