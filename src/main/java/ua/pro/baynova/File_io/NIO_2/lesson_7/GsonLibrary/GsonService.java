package ua.pro.baynova.File_io.NIO_2.lesson_7.GsonLibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonService {
    private static final Logger logger = LoggerFactory.getLogger(GsonService.class);

    public static void saveUsersToJSON(List<GsonUser> users, String filePath) {
        logger.info("-> СОХРАНЕНИЕ ПОЛЬЗОВАТЕЛЕЙ <-\n");
        logger.info("# Файл: {}", filePath);
        logger.info("() Пользователей: {}\n", users.size());

        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            logger.debug("# Gson объект создан с настройками");

            String jsonString = gson.toJson(users);
            logger.debug("# Объекты преобразованы в JSON");

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonString);
                writer.flush();
            }

            logger.info("-> Пользователи сохранены!");
            logger.info("(!) Пароли НЕ сохранены\n");

            showJSONContent(filePath);
        } catch (IOException e) {
            logger.error("(!) Ошибка при сохранении: {}", e.getMessage());
        }
    }

    public static List<GsonUser> loadUsersFromJSON(String filePath) {
        logger.info("-> ЗАГРУЗКА ПОЛЬЗОВАТЕЛЕЙ <-\n");
        logger.info("# Файл: {}\n", filePath);

        try {
            if (!Files.exists(Paths.get(filePath))) {
                logger.error("(!) Файл не найден");
                return new ArrayList<>();
            }

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            logger.debug("# Gson объект создан");

            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            logger.debug("-> JSON прочитан из файла");

            GsonUser[] usersArray = gson.fromJson(jsonString, GsonUser[].class);
            List<GsonUser> users = new ArrayList<>(Arrays.asList(usersArray));
            logger.info("# Загружено {} пользователей\n", users.size());
            return users;

        } catch (JsonSyntaxException e) {
            logger.error("(!) ОШИБКА СИНТАКСИСА JSON:");
            logger.error(" {}\n", e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            logger.error("(!) ОШИБКА ФАЙЛА:");
            logger.error(" {}\n", e.getMessage());
            return new ArrayList<>();
        }
    }

    public static String convertToJSONString(GsonUser user) {
        logger.info("-> КОНВЕРТАЦИЯ В JSON СТРОКУ <-\n");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String jsonString = gson.toJson(user);
        logger.info("# Пользователь: {}", user.username);
        logger.info("-> JSON строка:\n{}\n", jsonString);

        return jsonString;
    }

    public static GsonUser loadFromJSONString(String jsonString) {
        logger.info("-> ЗАГРУЗКА ИЗ JSON СТРОКИ <-\n");
        logger.info("# JSON: {}\n", jsonString);

        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            GsonUser user = gson.fromJson(jsonString, GsonUser.class);
            logger.info("-> Пользователь загружен из JSON строки\n");

            return user;

        } catch (JsonSyntaxException e) {
            logger.error("(!) Ошибка при загрузке из строки: {}", e.getMessage());
            return null;
        }
    }

    public static void displayUsers(List<GsonUser> users) {
        logger.info("-> ВСЕ ПОЛЬЗОВАТЕЛИ <-\n");
        for (int i = 0; i < users.size(); i++) {
            logger.info("{}. {}", i + 1, users.get(i));
            logger.info("");
        }
    }

    private static void showJSONContent(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            logger.info("# JSON файл:\n{}\n", content);
        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
    }
}
