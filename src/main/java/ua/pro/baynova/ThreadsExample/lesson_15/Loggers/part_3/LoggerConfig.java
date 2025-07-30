package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_3;

import java.util.logging.*;

public class LoggerConfig {
    public static void configureLogger(Level level) {
        Logger logger = Logger.getLogger(Library.class.getName());
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(level);
        handler.setFormatter(new SimpleFormatter());

        logger.addHandler(handler);
        logger.setLevel(level);
    }
}
