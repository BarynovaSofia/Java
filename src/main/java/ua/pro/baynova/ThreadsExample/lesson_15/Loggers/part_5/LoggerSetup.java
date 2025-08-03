package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.io.IOException;
import java.util.logging.*;

public class LoggerSetup {
    public static void setupLogger() throws IOException{
        Logger logger = Logger.getLogger("");

        for (Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);

        FileHandler fileHandler = new FileHandler("logs/errors.log", true);
        fileHandler.setLevel(Level.SEVERE);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

        logger.setLevel(Level.ALL);
    }
}
