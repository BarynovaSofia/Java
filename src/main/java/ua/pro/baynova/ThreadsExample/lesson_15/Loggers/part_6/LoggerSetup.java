package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

import java.io.IOException;
import java.util.logging.*;

public class LoggerSetup {

    public static void setup() throws IOException {
        LogManager.getLogManager().reset();

        Logger rootLogger = Logger.getLogger("");
        FileHandler appFileHandler = new FileHandler("logs/app.log" , true);
        appFileHandler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(appFileHandler);
        rootLogger.setLevel(Level.INFO);

        Logger validatorLogger = Logger.getLogger(RequestValidator.class.getName());
        FileHandler errorsHandler = new FileHandler("logs/errors.log", true);
        errorsHandler.setFormatter(new SimpleFormatter());
        errorsHandler.setLevel(Level.WARNING);
        validatorLogger.addHandler(errorsHandler);
        validatorLogger.setUseParentHandlers(false);

        Logger handlerLogger = Logger.getLogger(RequestHandler.class.getName());
        FileHandler handlerErrors = new FileHandler("logs/handler-errors.log", true);
        handlerErrors.setFormatter(new SimpleFormatter());
        handlerErrors.setLevel(Level.SEVERE);
        handlerLogger.addHandler(handlerErrors);
        handlerLogger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        rootLogger.addHandler(consoleHandler);

    }
}
