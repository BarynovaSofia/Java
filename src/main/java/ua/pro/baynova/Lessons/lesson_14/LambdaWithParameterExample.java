package ua.pro.baynova.Lessons.lesson_14;

public class LambdaWithParameterExample {
    public static void main(String[] args) {

        StringAction toUpperCase = (text) -> System.out.println(text.toUpperCase());
        StringAction countLength = (text) -> System.out.println("Длина строки: " + text.length());

        performAction("Привет, лямбда!", toUpperCase);
        performAction("Привет, лямбда!", countLength);

    }

    public static void performAction(String input, StringAction action){
        action.perform(input);
    }
}
