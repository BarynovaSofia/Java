package ua.pro.baynova.File_io.NIO_2.lesson_6.CinemaWithUsers;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    public String username;
    public String email;

    @JsonIgnore
    public String password;

    public int movieRating;
    public java.util.List<String> favoriteMovies;

    public User() {
        this.favoriteMovies = new java.util.ArrayList<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteMovies = new java.util.ArrayList<>();
    }

    public void addFavoriteMovie(String movieTitle) {
        this.favoriteMovies.add(movieTitle);
    }

    @Override
    public String toString() {
        return "# " + username + " (" + email + ")\n" +
                "   Пароль: " + (password != null ? "***скрыт***" : "нет") + "\n" +
                "   Рейтинг фильмов: " + movieRating + "/10\n" +
                "   Излюбленные: " + favoriteMovies;
    }
}
