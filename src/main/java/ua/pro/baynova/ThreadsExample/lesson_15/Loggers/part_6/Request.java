package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

public class Request {
    private final int id;
    private final String data;

    public Request(int id, String data){
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String toString(){
        return "Request{id=" + id + ", data='" + data + "'}";
    }
}
