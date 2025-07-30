package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_3;

import java.util.Arrays;
import java.util.logging.Level;

public class LibraryAppMain {
    public static void main(String[] args) throws InterruptedException {
        LoggerConfig.configureLogger(Level.FINE);

        Library library = new Library(Arrays.asList(
                "1984", "Мастер и Маргарита", "Преступление и наказание"
        ));

        new LibraryTaskRunner().runTasks(library);
    }
}
