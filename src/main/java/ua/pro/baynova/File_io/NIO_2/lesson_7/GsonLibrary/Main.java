package ua.pro.baynova.File_io.NIO_2.lesson_7.GsonLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Dell\\IdeaProjects\\Java\\gson_users.json";

        logger.info(" СЦЕНАРИЙ 1: Создание пользователей \n");
        GsonUser user1 = new GsonUser("alice_wonder", "alice@example.com", "secretPass123");
        user1.movieRating = 9;
        user1.addFavoriteMovie("Матрица");
        user1.addFavoriteMovie("Начало");

        GsonUser user2 = new GsonUser("bob_builder", "bob@example.com", "bobSecret456");
        user2.movieRating = 8;
        user2.addFavoriteMovie("Интерстеллар");

        GsonUser user3 = new GsonUser("charlie_brown", "charlie@example.com", "charliePass789");
        user3.movieRating = 7;
        user3.addFavoriteMovie("Форрест Гамп");

        logger.info("# Создание 3 пользователей с паролями\n");
        logger.info("В программе видны пароли:");
        logger.info("  user1.password = {}", user1.password);
        logger.info("  user2.password = {}", user2.password);
        logger.info("  user3.password = {}\n", user3.password);

        logger.info(" СЦЕНАРИЙ 2: Сохранение в JSON с GSON \n");
        List<GsonUser> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        GsonService.saveUsersToJSON(users, filePath);

        logger.info(" СЦЕНАРИЙ 3: Загрузка с GSON \n");
        List<GsonUser> loadedUsers = GsonService.loadUsersFromJSON(filePath);
        GsonService.displayUsers(loadedUsers);


        logger.info(" СЦЕНАРИЙ 4: JSON строка \n");
        String jsonString = GsonService.convertToJSONString(loadedUsers.get(0));

        logger.info(" СЦЕНАРИЙ 5: Загрузка из JSON строки \n");
        GsonUser userFromString = GsonService.loadFromJSONString(jsonString);

        logger.info("Загруженный пользователь:\n{}\n", userFromString);
        logger.info("-> ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА!\n");
    }
}
