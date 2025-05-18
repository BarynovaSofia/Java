package ua.pro.baynova.JUnitExamle;

public class EmailValidator {
    public boolean isValidEmail(String email){
        if (email == null || email.isBlank()){
            return false;
        }

        int atIndex = email.indexOf("@");
        if (atIndex < 1 || atIndex != email.lastIndexOf("@")){
            return false;
        }

        String domain = email.substring(atIndex + 1);
        return domain.contains(".");
    }
}
