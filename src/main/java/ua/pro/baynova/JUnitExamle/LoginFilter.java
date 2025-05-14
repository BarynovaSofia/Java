package ua.pro.baynova.JUnitExamle;

import java.util.ArrayList;
import java.util.List;

public class LoginFilter {
    public List<String> filterValidLogins(List<String> logins){
        List<String> valid = new ArrayList<>();

        for (String login : logins){
            if (login == null || login.isEmpty()) continue;

            if (!Character.isLetter(login.charAt(0))) continue;

            if (!login.matches("[a-zA-Z0-9]+")) continue;

            if (login.length() < 4 || login.length() > 10) continue;

            valid.add(login);
        }
        return valid;
    }
}
