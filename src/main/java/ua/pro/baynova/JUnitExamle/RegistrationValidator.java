package ua.pro.baynova.JUnitExamle;

public class RegistrationValidator {

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Ім'я не повинно бути пустим або містити цифри");
        }
    }

    public void validateEmail(String email){
        if (email == null || !email.contains("@")){
            throw new IllegalArgumentException("Некорректный email");
        }
    }

    public void validatePassword(String password){
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*")){
            throw new IllegalArgumentException("Пароль должен содержать не менее 8 символов и хотя бы одну цифру");
        }
    }
}
