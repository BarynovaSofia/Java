package ua.pro.baynova.lessons.lesson_13;

public class ThrowExample {
    public static void main(String[] args) {
        int age = 16;

        try {
            checkAge(age);
            System.out.println("Доступ разрешён!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Доступ запрещён: возраст должен быть 18+");
        }
    }
}