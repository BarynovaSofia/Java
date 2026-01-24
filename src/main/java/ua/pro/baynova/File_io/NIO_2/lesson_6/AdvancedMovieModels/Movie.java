package ua.pro.baynova.File_io.NIO_2.lesson_6.AdvancedMovieModels;

public class Movie {
    public String title;
    public int year;
    public double rating;
    public Director director;

    public java.util.List<Actor> actors;
    @com.fasterxml.jackson.annotation.JsonProperty("imdb_score")
    public double imdbScore;
    public Movie() {
        this.actors = new java.util.ArrayList<>();
    }

    public Movie(String title, int year, double rating, Director director) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.actors = new java.util.ArrayList<>();
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    @Override
    public String toString() {
        return "# " + title + " (" + year + ")\n" +
                "   Режиссёр: " + director + "\n" +
                "   Рейтинг: " + rating + "/10\n" +
                "   IMDB: " + imdbScore + "/10\n" +
                "   Актёры: " + actors;
    }
}
