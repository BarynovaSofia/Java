package ua.pro.baynova.File_io.NIO_2.lesson_6.CinemaWithUsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AdvancedJacksonService {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedJacksonService.class);

    public static void saveUsersToJSON(List<User> users, String filePath) {
        logger.info("-> СОХРАНЕНИЕ ПОЛЬЗОВАТЕЛЕЙ <-\n");
        logger.info("# Файл: {}", filePath);
        logger.info("# Пользователей: {}\n", users.size());

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(filePath), users);

            logger.info("# Пользователи сохранены!");
            logger.info("# Важно: Пароли НЕ сохранены в JSON\n");

            showJSONContent(filePath);
        } catch (IOException e) {
            logger.error("(!) Ошибка при сохранении: {}", e.getMessage());
        }
    }

    public static List<User> loadUsersFromJSON(String filePath) {
        logger.info("-> ЗАГРУЗКА ПОЛЬЗОВАТЕЛЕЙ <-\n");
        logger.info("# Файл: {}\n", filePath);

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.error("(!) Файл не найден");
                return new ArrayList<>();
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            logger.debug("# Настроена обработка неизвестных полей");

            User[] userArrays = mapper.readValue(file, User[].class);
            logger.debug("# Jackson успешно прочитал JSON");

            List<User> users = new ArrayList<>();
            for (User user : userArrays) {
                users.add(user);
            }

            logger.info("# Загружено {} пользователей\n", users.size());
            return users;
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            logger.error("(!) ОШИБКА СИНТАКСИСА JSON:");
            logger.error("   На строке: {}", e.getLocation().getLineNr());
            logger.error("   Колонка: {}", e.getLocation().getColumnNr());
            logger.error("   Проблема: {}\n", e.getOriginalMessage());
            return new ArrayList<>();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            logger.error("(!) ОШИБКА МАППИНГА (неправильный тип данных):");
            logger.error("   Поле: {}", e.getPathReference());
            logger.error("   Проблема: Невозможно преобразовать значение\n");
            return new ArrayList<>();

        } catch (IOException e) {
            logger.error("(!) ОШИБКА ФАЙЛА:");
            logger.error("   Проблема: {}\n", e.getMessage());
            return new ArrayList<>();
        }
    }

    public static String convertToJSONString(User user) {
        logger.info("-> КОНВЕРТАЦИЯ В JSON СТРОКУ <-\n");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);

            logger.info("# Пользователь: {}", user.username);
            logger.info("-> JSON строка:\n{}\n", jsonString);

            return jsonString;
        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
            return null;
        }
    }

    public static User loadFromJSONString(String jsonString) {
        logger.info("-> ЗАГРУЗКА ИЗ JSON СТРОКИ <-\n");
        logger.info("# JSON: {}\n", jsonString);

        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(jsonString, User.class);

            logger.info("# Пользователь загружен из JSON строки\n");
            return user;
        } catch (IOException e) {
            logger.error("(!) Ошибка при загрузке из строки: {}", e.getMessage());
            return null;
        }
    }

    public static void demonstrateUnknownFields() {
        logger.info("-> НЕИЗВЕСТНЫЕ ПОЛЯ <-\n");
        String apiJSON = """
                {
                "username": "john_doe",
                              "email": "john@example.com",
                              "movieRating": 9,
                              "favoriteMovies": ["Матрица", "Начало"],
                              "api_version": "2.0",
                              "server_timestamp": "2024-01-15T10:30:00Z",
                              "request_id": "abc123"
                }
                """;

        logger.info("# JSON с ЛИШНИМИ полями:");
        logger.info(" >  api_version: \"2.0\" - нет в User класса");
        logger.info(" >  server_timestamp - нет в User класса");
        logger.info(" >  request_id - нет в User класса\n");

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            User user = mapper.readValue(apiJSON, User.class);

            logger.info("# SUCCESS! Jackson загрузил пользователя");
            logger.info("Лишние поля были проигнорированы\n");
            logger.info("Загруженный пользователь: {}\n", user);

        } catch (IOException e) {
            logger.error("(!) ОШИБКА: {}", e.getMessage());
        }
    }

    public static void displayUsers(List<User> users) {
        logger.info("-> ВСЕ ПОЛЬЗОВАТЕЛИ <-\n");

        for (int i = 0; i < users.size(); i++) {
            logger.info("{}. {}", i + 1, users.get(i));
            logger.info("");
        }
    }

    private static void showJSONContent(String filePath) {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filePath);
            String content = new String(java.nio.file.Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            logger.info("# JSON файл:\n{}\n", content);
        } catch (IOException e) {
            logger.error("Ошибка: {}", e.getMessage());
        }
    }
}
