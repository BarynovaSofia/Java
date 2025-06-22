package ua.pro.baynova.ThreadsExample.lesson_1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

class HelloRunnableTaskTest {

    @Test
    void threadShouldReturnHelloMessage() throws ExecutionException, InterruptedException{

        Callable<String> task = new HelloRunnableTask();

        ExecutorService executor  = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(task);
        String result = future.get();

        executor.shutdown();

        assertEquals("Привет из потока!", result);
    }
}