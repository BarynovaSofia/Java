package ua.pro.baynova.File_io.NIO_2.lesson_6.AdvancedMovieModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdvancedMovieService {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedMovieService.class);

    public static void saveMoviesToJSON(List<Movie> movies, String filePath) {
        logger.info("-> СОХРАНЕНИЕ СЛОЖНЫХ ФИЛЬМОВ <-\n");
        logger.info("# Файл: {}", filePath);
        logger.info("# Фильмов: {}\n", movies.size());

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(filePath), movies);

            logger.info("-> Фильмы сохранены\n");
            showJSONContent(filePath);

        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
    }

    public static List<Movie> loadMoviesFromJSON(String filePath) {
        logger.info("-> ЗАГРУЗКА СЛОЖНЫХ ФИЛЬМОВ <-\n");
        logger.info("# Файл: {}\n", filePath);

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.error("!# Файл не найден");
                return new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();
            Movie[] moviesArray = mapper.readValue(file, Movie[].class);

            List<Movie> movies = new ArrayList<>();
            for (Movie movie : moviesArray) {
                movies.add(movie);
            }

            logger.info("-> Загружено {} фильмов\n", movies.size());
            return movies;

        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void displayMovies(List<Movie> movies) {
        logger.info("-> ВСЕ ФИЛЬМЫ <-\n");

        for (int i = 0; i < movies.size(); i++) {
            logger.info("{}) {}", i + 1, movies.get(i));
            logger.info("");
        }
    }

    private static void showJSONContent(String filePath) {
        logger.info("# Содержимое JSON:\n");

        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filePath);
            String content = new String(java.nio.file.Files.readAllBytes(path),
                    java.nio.charset.StandardCharsets.UTF_8);
            logger.info(content);
            logger.info("");

        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
    }
}
