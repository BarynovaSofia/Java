package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

public class Pizza {
    private final int id;
    private final String type;

    public Pizza(int id, String type){
        this.id = id;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }
}
