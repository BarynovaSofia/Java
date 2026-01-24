package ua.pro.baynova.File_io.NIO_2.lesson_6.MovieLibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Dell\\IdeaProjects\\Java\\movies.json";

        logger.info("\n ДЕМОНСТРАЦИЯ JACKSON - СОХРАНЕНИЕ И ЗАГРУЗКА ФИЛЬМОВ\n");
        logger.info("--- СЦЕНАРИЙ 1: Создаю список фильмов и сохраняю в JSON ---\n");
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Матрица", 1999, 8.7, "Вачовские"));
        movies.add(new Movie("Начало", 2010, 8.8, "Кристофер Нолан"));
        movies.add(new Movie("Интерстеллар", 2014, 8.7, "Кристофер Нолан"));
        movies.add(new Movie("Форрест Гамп", 1994, 8.8, "Роберт Земекис"));

        logger.info(" Создал список с {} фильмами\n", movies.size());

        MovieLibraryService.displayMovies(movies);
        MovieLibraryService.saveMoviesToJSON(movies, filePath);

        logger.info("--- СЦЕНАРИЙ 2: Загружаю фильмы из JSON файла ---\n");
        List<Movie> loadedMovies = MovieLibraryService.loadMoviesFromJSON(filePath);
        MovieLibraryService.displayMovies(loadedMovies);

        logger.info("--- СЦЕНАРИЙ 3: Ищу конкретный фильм ---\n");
        Movie found = MovieLibraryService.findMovieByTitle(loadedMovies, "Интерстеллар");

        logger.info("--- СЦЕНАРИЙ 4: Добавляю новый фильм в JSON ---\n");
        Movie newMovie = new Movie("Титаник", 1997, 7.9, "Джеймс Кэмерон");
        MovieLibraryService.addMovieToJSON(filePath, newMovie);

        logger.info("--- СЦЕНАРИЙ 5: Загружаю обновленный список ---\n");
        List<Movie> updatedMovies = MovieLibraryService.loadMoviesFromJSON(filePath);
        MovieLibraryService.displayMovies(updatedMovies);

        logger.info(" Демонстрация завершена!\n");
    }
}
