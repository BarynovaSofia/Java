package ua.pro.baynova.ThreadsExample.lesson_11;

//Класс с volatile, управляющий состоянием
public class AtomicServerFlag implements ServerFlag {
    private volatile boolean running = true;

    @Override
    public boolean isRunning() {
        return running;
    }

    public void stop(){
        running = false;
    }
}
