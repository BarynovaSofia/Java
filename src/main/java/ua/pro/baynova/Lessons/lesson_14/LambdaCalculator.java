package ua.pro.baynova.Lessons.lesson_14;

public class LambdaCalculator {

    public static void main(String[] args) {// Операции с лямбдами
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> {
            if (b == 0) {
                System.out.println("Деление на ноль!");
                return 0;
            }
            return a / b;
        };

        performCalculation(10, 5, add, "Сложение");
        performCalculation(25, 3, subtract, "Вычитание");
        performCalculation(34, 8, multiply, "Умножение");
        performCalculation(68, 7, divide, "Деление");
    }

    public static void performCalculation(int a, int b, Calculator operation, String operationName) {
        System.out.println(operationName + ": " + operation.operate(a, b));
    }
}