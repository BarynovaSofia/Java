package ua.pro.baynova.File_io.NIO_2.lesson_6.MovieLibraryService;

public class Movie {
    public String title;
    public int year;
    public double rating;
    public String director;

    public Movie() {

    }

    public Movie(String title, int year, double rating, String director) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", director='" + director + '\'' +
                '}';
    }
}
