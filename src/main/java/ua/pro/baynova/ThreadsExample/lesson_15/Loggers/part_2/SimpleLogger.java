package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger {
    public enum Level{ DEBUG, INFO, WARN, ERROR, FATAL }

    private final Level currentLevel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public SimpleLogger(Level level){
        this.currentLevel = level;
    }

    public synchronized void log(Level level, String message){
        if (level.ordinal() >= currentLevel.ordinal()){
            String time = sdf.format(new Date());
            System.out.printf("[%s] [%s] %s %s%n",
                    time,
                    Thread.currentThread().getName(),
                    level,
                    message
            );
        }
    }

    public void debug(String message) { log(Level.DEBUG, message); }
    public void info(String message) { log(Level.INFO, message); }
    public void warn(String message) { log(Level.WARN, message); }
    public void error(String message) { log(Level.ERROR, message); }
    public void fatal(String message) { log(Level.FATAL, message); }
}
