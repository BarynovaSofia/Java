package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSetup {

    public static Logger createLogger(Class<?> clazz) throws IOException{
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);

        File logDir = new File("logs");
        if (!logDir.exists()) {
            boolean created = logDir.mkdir();
            if (!created) {
                throw new IOException("Не удалось создать папку логов: " + logDir.getAbsolutePath());
            }
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new CustomFormatter());
        logger.addHandler(consoleHandler);


        FileHandler fileHandler = new FileHandler("logs/bakery.log", true);
        fileHandler.setLevel(Level.WARNING);
        fileHandler.setFormatter(new CustomFormatter());
        logger.addHandler(fileHandler);

        logger.setLevel(Level.ALL);
        return logger;
    }
}
