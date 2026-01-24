package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import java.io.Serializable;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String genre;
    private int year;
    private double rating;

    public Movie(String title, String genre, int year, double rating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - %s | # %.1f", title, year, genre, rating);
    }
}
