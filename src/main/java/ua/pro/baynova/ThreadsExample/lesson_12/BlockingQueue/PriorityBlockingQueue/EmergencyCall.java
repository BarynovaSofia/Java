package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

public class EmergencyCall implements Comparable<EmergencyCall> {
    private final int priority;
    private final String description;

    public EmergencyCall(int priority, String description){
        this.priority = priority;
        this.description = description;
    }

    public int getPriority(){
        return priority;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public int compareTo(EmergencyCall other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Вызов: " + description + " (приоритет: " + priority + ")";
    }
}
