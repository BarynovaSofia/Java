package ua.pro.baynova.TDD_Example.lesson_5;

public class Task {
    private final String id;
    private final String description;
    private final boolean important;
    private boolean completed;

    public Task(String id, String description, boolean important){
        this.id = id;
        this.description = description;
        this.important = important;
        this.completed = false;
    }

    public String getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public boolean isImportant(){
        return important;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void markCompleted(){
        this.completed = true;
    }
}
