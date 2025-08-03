package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerSetup {
    public static Logger setup() throws IOException {
        Logger logger = Logger.getLogger("OrderLogger");
        logger.setUseParentHandlers(false);

        for (Handler h : logger.getHandlers()) {
            logger.removeHandler(h);
        }

        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        FileHandler appLog = new FileHandler("logs/app.log", true);
        appLog.setLevel(Level.INFO);
        appLog.setFormatter(new SimpleFormatter());
        logger.addHandler(appLog);

        FileHandler errorLog = new FileHandler("logs/errors.log", true);
        errorLog.setLevel(Level.SEVERE);
        errorLog.setFormatter(new SimpleFormatter());
        logger.addHandler(errorLog);

        logger.setLevel(Level.ALL);

        return logger;
    }
}

