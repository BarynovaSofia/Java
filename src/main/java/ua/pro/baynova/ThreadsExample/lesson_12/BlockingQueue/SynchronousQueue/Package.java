package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.SynchronousQueue;

public class Package {
    private final String id;
    private final String content;

    public Package(String id, String content){
        this.id = id;
        this.content = content;
    }

    public String getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    @Override
    public String toString(){
        return "📦 Посылка #" + id + " с содержимым: " + content;
    }
}
