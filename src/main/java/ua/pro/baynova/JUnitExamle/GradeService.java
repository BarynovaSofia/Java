package ua.pro.baynova.JUnitExamle;

public class GradeService {

    public String getLetterGrade(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Оценка должна быть от 0 до 100");
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}