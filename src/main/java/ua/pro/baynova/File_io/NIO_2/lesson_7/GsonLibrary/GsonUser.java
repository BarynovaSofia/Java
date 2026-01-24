package ua.pro.baynova.File_io.NIO_2.lesson_7.GsonLibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonUser {
    @SerializedName("user_name")
    public String username;

    public String email;

    @Expose(serialize = false, deserialize = false)
    public String password;

    public int movieRating;
    public java.util.List<String> favoriteMovies;

    public GsonUser() {
        this.favoriteMovies = new java.util.ArrayList<>();
    }

    public GsonUser(String username, String email, String password) {
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
        return "#" + username + " (" + email + ")\n" +
                "   Пароль: " + (password != null ? ">скрыт<" : "нет") + "\n" +
                "   Рейтинг фильмов: " + movieRating + "/10\n" +
                "   Любимые: " + favoriteMovies;
    }
}
