package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DiscLibraryApp {
    private static final Logger logger = LoggerFactory.getLogger(DiscLibraryApp.class);

    public static void main(String[] args) {
        logger.info("");

        Disc disc = new Disc("My Favorite Movies");
        disc.addMovies(new Movie("Interstellar", "Sci-Fi", 2014, 8.6));
        disc.addMovies(new Movie("Spirited Away", "Anime", 2001, 8.5));
        disc.addMovies(new Movie("The Dark Knight", "Action", 2008, 9.0));
        disc.addMovies(new Movie("Amélie", "Romance", 2001, 8.3));

        String filePaths = "C:\\Users\\Dell\\Downloads\\disc_save.ser";
        DiscWriter writer = new DiscWriter();
        writer.writerDisc(disc, filePaths);

        DiscReader reader = new DiscReader();
        Disc loaded = reader.readerDisc(filePaths);
        reader.readerDisc(filePaths);

        if (loaded != null) {
            logger.info("-> Загружен диск: {}", loaded.getDiscName());
            List<Movie> movies = loaded.getMovies();
            movies.forEach(movie -> logger.info("@ {}", movie));
        }

        logger.info(">>> Завершение DiscLibraryApp <<<");
    }
}
