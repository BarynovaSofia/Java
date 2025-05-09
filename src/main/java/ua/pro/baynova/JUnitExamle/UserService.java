package ua.pro.baynova.JUnitExamle;

public class UserService {

    public String greetUser(String name) {
        if (name == null || name.isBlank()) {
            return "Привет, гость!!!";
        }

        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Имя не должно содержать цифры!!!");
        }

        if (name.matches(".*[A-Za-z].*")){
            throw new IllegalArgumentException("Имя должно быть на кириллице");
        }

        return "Привет, " + name + "!!!";
    }
}
