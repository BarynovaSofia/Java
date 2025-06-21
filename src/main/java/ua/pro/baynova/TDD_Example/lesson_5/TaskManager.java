package ua.pro.baynova.TDD_Example.lesson_5;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private final NotificationService notificationService;
    private final LoggerService loggerService;

    public TaskManager(NotificationService notificationService, LoggerService loggerService){
        this.notificationService = notificationService;
        this.loggerService = loggerService;
    }

    public void addTask(Task task){
        long activeCount = tasks.stream()
                .filter(t -> !t.isCompleted())
                .count();

        if (activeCount >= 5 && !task.isCompleted()) {
            throw new IllegalStateException("Too many active tasks");
        }

        tasks.add(task);

        loggerService.log("Added task: " + task.getDescription());

        if (task.isImportant()) {
            notificationService.notifyImportant(task);
        }
    }

    public List<Task> getActiveTasks(){
        List<Task> active = new ArrayList<>();
        for (Task task : tasks){
            if (!task.isCompleted()){
                active.add(task);
            }
        }
        return active;
    }

    public void completeTask(String id){
        for (Task task : tasks){
            if (task.getId().equals(id)){
                task.markCompleted();
                return;
            }
        }
    }

    public List<Task> getCompletedTasks(){
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks){
            if (task.isCompleted()){
                completed.add(task);
            }
        }
        return completed;
    }

    public void removeTask(String id){
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public List<Task> getImportantTasks(){
        List<Task> important = new ArrayList<>();
        for (Task task : tasks){
            if (task.isImportant()){
                important.add(task);
            }
        }
        return important;
    }

    public Task getTaskById(String id){
        for (Task task : tasks){
            if (task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }
}
