package ua.pro.baynova.File_io.NIO_2.lesson_6.MovieLibraryService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieLibraryService {
    private static final Logger logger = LoggerFactory.getLogger(MovieLibraryService.class);
    public static void saveMoviesToJSON(List<Movie> movies, String filePath) {
        logger.info("=== СОХРАНЕНИЕ ФИЛЬМОВ В JSON ===\n");
        logger.info(" Файл: {}", filePath);
        logger.info(" Фильмов для сохранения: {}\n", movies.size());

        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.debug(" ObjectMapper создан");
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            logger.debug(" Красивое форматирование включено");

            mapper.writeValue(new File(filePath), movies);
            logger.debug("Jackson записал JSON в файл");
            logger.info("Фильмы успешно сохранены!\n");

            showJSONContent(filePath);

        } catch (IOException e) {
            logger.error(" Ошибка при сохранении: {}", e.getMessage());
        }
    }

    public static List<Movie> loadMoviesFromJSON(String filePath) {
        logger.info("=== ЗАГРУЗКА ФИЛЬМОВ ИЗ JSON ===\n");
        logger.info("Файл: {}\n", filePath);

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.error("Файл не найден: {}", filePath);
                return new ArrayList<>();
            }
            logger.debug("Файл найден");
            ObjectMapper mapper = new ObjectMapper();
            logger.debug("ObjectMapper создан");

            Movie[] moviesArray = mapper.readValue(file, Movie[].class);
            logger.debug("Jackson прочитал JSON из файла");
            List<Movie> movies = new ArrayList<>();
            for (Movie movie : moviesArray) {
                movies.add(movie);
            }

            logger.info("Фильмы успешно загружены!");
            logger.info("Загружено фильмов: {}\n", movies.size());

            return movies;

        } catch (IOException e) {
            logger.error("Ошибка при загрузке: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void addMovieToJSON(String filePath, Movie newMovie) {
        logger.info("=== ДОБАВЛЕНИЕ ФИЛЬМА ===\n");
        logger.info("Файл: {}", filePath);
        logger.info("Добавляю: {}\n", newMovie.getTitle());

        List<Movie> movies = loadMoviesFromJSON(filePath);
        movies.add(newMovie);
        logger.debug("Фильм добавлен в список");

        saveMoviesToJSON(movies, filePath);
        logger.info("");
    }

    private static void showJSONContent(String filePath) {
        logger.info("Содержимое JSON файла:\n");

        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filePath);
            String content = new String(java.nio.file.Files.readAllBytes(path),
                    java.nio.charset.StandardCharsets.UTF_8);

            logger.info(content);
            logger.info("");

        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: {}", e.getMessage());
        }
    }

    public static void displayMovies(List<Movie> movies) {
        logger.info("=== СПИСОК ФИЛЬМОВ ===\n");

        if (movies.isEmpty()) {
            logger.info("Список пуст\n");
            return;
        }

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            logger.info("{}. {} ({}) - Режиссёр: {} - Рейтинг: {}",
                    i + 1,
                    movie.getTitle(),
                    movie.getYear(),
                    movie.getDirector(),
                    movie.getRating()
            );
        }

        logger.info("\n");
    }

    public static Movie findMovieByTitle(List<Movie> movies, String title) {
        logger.info("=== ПОИСК ФИЛЬМА ===\n");
        logger.info("Ищу: {}\n", title);

        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                logger.info("Найден: {}\n", movie);
                return movie;
            }
        }

        logger.info("Фильм не найден\n");
        return null;
    }
}