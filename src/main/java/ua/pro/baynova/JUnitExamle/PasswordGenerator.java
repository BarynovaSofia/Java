package ua.pro.baynova.JUnitExamle;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private  final SecureRandom random = new SecureRandom();

    public String generatePassword(int length){
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            password.append(randomChar);
        }
        return password.toString();
    }
}
