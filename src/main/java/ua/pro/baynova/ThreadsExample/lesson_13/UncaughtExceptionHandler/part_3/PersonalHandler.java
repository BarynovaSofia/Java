package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_3;

public class PersonalHandler implements Thread.UncaughtExceptionHandler {
    private final String curator;

    public PersonalHandler(String curator){
        this.curator = curator;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("🧑‍💼 " + curator + ": Обнаружена ошибка у оператора "
                + t.getName() + ": " + e.getMessage());
    }

    @Override
    public String toString(){
        return "👤 Куратор " + curator;
    }
}
