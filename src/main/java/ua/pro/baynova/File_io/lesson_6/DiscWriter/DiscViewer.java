package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DiscViewer {

    private static final Logger logger = LoggerFactory.getLogger(DiscViewer.class);

    public static void main(String[] args) {
        logger.info(">>> Запуск DiscViewer");

        if (args.length == 0) {
            logger.error("!!! Укажите путь к .ser файлу, например: DiscViewer C:\\Users\\Dell\\Downloads\\disc_save.ser");
            return;
        }

        String filePath = args[0];

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();

            if (obj instanceof Disc disc) {
                logger.info("() Файл успешно прочитан: {}", filePath);
                printDiscDetails(disc);
            } else {
                logger.warn("--> Файл '{}' не содержит объект типа Disc (найден {})",
                        filePath, obj.getClass().getName());
            }

        } catch (IOException e) {
            logger.error("!!! Ошибка ввода-вывода при чтении файла '{}': {}", filePath, e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("!!! Класс для десериализации не найден: {}", e.getMessage());
        }
    }

    private static void printDiscDetails(Disc disc) {
        System.out.println("\n================");
        System.out.println("() ДИСК: " + disc.getDiscName());
        System.out.println("================");
        List<Movie> movies = disc.getMovies();

        if (movies.isEmpty()) {
            System.out.println("() На диске нет фильмов.");
        } else {
            for (int i = 0; i < movies.size(); i++) {
                System.out.printf("%2d. -> %s%n", i + 1, movies.get(i));
            }
        }
        System.out.println("=============\n");
    }
}

