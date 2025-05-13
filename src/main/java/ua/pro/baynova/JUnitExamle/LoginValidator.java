package ua.pro.baynova.JUnitExamle;

public class LoginValidator {

    public void validateLogin(String login){
        if (login == null || login.isEmpty()){
            throw new IllegalArgumentException("Login cannot be empty");
        }

        if (!Character.isLetter(login.charAt(0))){
            throw new IllegalArgumentException("Login must start with a letter");
        }

        if (!login.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("Login must contain only letters and digits");
        }

        if (login.length() < 4 || login.length() > 12){
            throw new IllegalArgumentException("Login must be between 4 and 12 characters long");
        }
    }
}
