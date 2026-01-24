package ua.pro.baynova.File_io.NIO_2.lesson_6.AdvancedMovieModels;

public class Actor {
    public String name;
    public String role;

    public Actor() {
    }

    public Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
