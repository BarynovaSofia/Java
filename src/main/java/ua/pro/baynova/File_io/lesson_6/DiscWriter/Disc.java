package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disc implements Serializable {
    private static final long serialVersionUID = 1L;

    private String discName;
    private List<Movie> movies = new ArrayList<>();

    public Disc(String discName) {
        this.discName = discName;
    }

    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    public String getDiscName() {
        return discName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "() Диск: " + discName + " | Фильмы: " + movies.size();
    }
}
