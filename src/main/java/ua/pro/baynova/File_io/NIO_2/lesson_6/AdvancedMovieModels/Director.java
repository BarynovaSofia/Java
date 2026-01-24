package ua.pro.baynova.File_io.NIO_2.lesson_6.AdvancedMovieModels;

public class Director {
    public String name;
    public int birthYear;
    public String country;

    public Director() {
    }

    public Director(String name, int birthYear, String country) {
        this.name = name;
        this.birthYear = birthYear;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ", род. " + birthYear + ")";
    }
}

