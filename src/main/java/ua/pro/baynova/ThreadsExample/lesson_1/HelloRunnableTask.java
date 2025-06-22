package ua.pro.baynova.ThreadsExample.lesson_1;

import java.util.concurrent.Callable;

public class HelloRunnableTask implements Callable<String> {

    @Override
    public String call(){
        return "Привет из потока!";
    }
}
