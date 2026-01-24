package ua.pro.baynova.File_io.NIO_2.lesson_6.AdvancedMovieModels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Dell\\IdeaProjects\\Java\\advanced_movies.json";

        logger.info("--> ШАГИ СОЗДАНИЯ <--\n");
        Director nolan = new Director("Кристофер Нолан", 1970, "Великобритания");
        Director spielberg = new Director("Стивен Спилберг", 1946, "США");
        logger.info("# Созданы режиссёры\n");

        Movie inception = new Movie("Начало", 2010, 8.8, nolan);
        inception.imdbScore = 8.8;

        inception.addActor(new Actor("Леонардо ДиКаприо", "Кобб"));
        inception.addActor(new Actor("Марион Котийяр", "Мал"));
        inception.addActor(new Actor("Джозеф Гордон-Левитт", "Артур"));

        logger.info("# Создан фильм 'Начало' с актёрами\n");

        Movie interstellar = new Movie("Интерстеллар", 2014, 8.7, nolan);
        interstellar.imdbScore = 8.7;
        interstellar.addActor(new Actor("Мэттью Макконахи", "Купер"));
        interstellar.addActor(new Actor("Энн Хэтэуэй", "Бранд"));

        logger.info("-> Создан фильм 'Интерстеллар' с актёрами\n");

        Movie jaws = new Movie("Челюсти", 1975, 8.0, spielberg);
        jaws.imdbScore = 8.0;
        jaws.addActor(new Actor("Роберт Шоу", "Квинт"));
        jaws.addActor(new Actor("Ричард Дрейфусс", "Гувер"));

        logger.info("-> Создан фильм 'Челюсти' с актёрами\n");
        List<Movie> movies = new ArrayList<>();
        movies.add(inception);
        movies.add(interstellar);
        movies.add(jaws);

        logger.info("--> СОХРАНЕНИЕ В JSON <--\n");
        AdvancedMovieService.saveMoviesToJSON(movies, filePath);

        logger.info("--> ЗАГРУЗКА ИЗ JSON <--\n");
        List<Movie> loadedMovies = AdvancedMovieService.loadMoviesFromJSON(filePath);

        logger.info("--> ЗАГРУЖЕННЫЕ ФИЛЬМЫ из JSON <--\n");
        AdvancedMovieService.displayMovies(loadedMovies);

        logger.info("-> ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА!\n");
        logger.info("# Jackson автоматически обработал:\n");
        logger.info("# Вложенные объекты (Director)\n");
        logger.info("# Списки объектов (List<Actor>)\n");
        logger.info("# Аннотации @JsonProperty (imdb_score)\n");
    }
}
