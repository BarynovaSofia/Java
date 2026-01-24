package ua.pro.baynova.File_io.NIO_2.lesson_6.CinemaWithUsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Dell\\IdeaProjects\\Java\\users.json";
        logger.info("\nПРОДВИНУТЫЙ JACKSON\n");

        logger.info("-> СЦЕНАРИЙ 1: Создание пользователей с паролями <-\n");
        User user1 = new User("john_doe", "john@example.com", "superSecretPassword123");
        user1.movieRating = 9;
        user1.addFavoriteMovie("Матрица");
        user1.addFavoriteMovie("Начало");

        User user2 = new User("jane_smith", "jane@example.com", "anotherSecret456");
        user2.movieRating = 8;
        user2.addFavoriteMovie("Интерстеллар");
        user2.addFavoriteMovie("Челюсти");

        User user3 = new User("bob_wilson", "bob@example.com", "bobPassword789");
        user3.movieRating = 7;
        user3.addFavoriteMovie("Форрест Гамп");

        logger.info("# 3 пользователей с паролями\n");

        logger.info("В программе пароли видны:");
        logger.info("user1.password = {}", user1.password);
        logger.info("user2.password = {}", user2.password);
        logger.info("user3.password = {}\n", user3.password);

        logger.info("-> СЦЕНАРИЙ 2: Сохранение в JSON файл <-\n");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        AdvancedJacksonService.saveUsersToJSON(users, filePath);

        logger.info("-> СЦЕНАРИЙ 3: Загрузка из JSON файла <-\n");
        List<User> loadedUsers = AdvancedJacksonService.loadUsersFromJSON(filePath);
        AdvancedJacksonService.displayUsers(loadedUsers);

        logger.info("-> СЦЕНАРИЙ 4: Конвертация объекта в JSON СТРОКУ <-\n");
        String jsonString = AdvancedJacksonService.convertToJSONString(loadedUsers.get(0));

        logger.info("-> СЦЕНАРИЙ 5: Загрузка объекта из JSON СТРОКИ <-\n");
        User userFromString = AdvancedJacksonService.loadFromJSONString(jsonString);
        logger.info("Загруженный пользователь:\n{}\n", userFromString);

        logger.info("-> СЦЕНАРИЙ 6: JSON с лишними полями от API <-\n");
        AdvancedJacksonService.demonstrateUnknownFields();

        logger.info("-> ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА <-\n");
    }
}
