package ua.pro.baynova.ThreadsExample.lesson_8.Demo_exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExceptionDemoTest {

    @Mock
    private ExecutorService executor;

    @Mock
    private Future<String> successFuture;

    @Mock
    private Future<String> failureFuture;

    private ExceptionDemo exceptionDemo;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        exceptionDemo = new ExceptionDemo(executor);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    @Test
    void runTasks_shouldPrintSuccessAndHandleFailure() throws Exception{
        when(executor.submit(any(Callable.class)))
                .thenReturn(successFuture)
                .thenReturn(failureFuture);

        when(successFuture.get()).thenReturn("✅ Всё прошло успешно!");

        when(failureFuture.get()).thenThrow(new ExecutionException(new IllegalArgumentException("💥 Упс, что-то пошло не так!")));

        exceptionDemo.runTasks();

        String output = outContent.toString();

        assertTrue(output.contains("Результат goodTask: ✅ Всё прошло успешно!"));
        assertTrue(output.contains("❗ ExecutionException:"));
        assertTrue(output.contains("🎯 getCause(): java.lang.IllegalArgumentException: 💥 Упс, что-то пошло не так!"));
        assertTrue(output.contains("📝 getMessage(): 💥 Упс, что-то пошло не так!"));

        verify(executor , times(2)).submit(any(Callable.class));
    }

    @Test
    void shutdown_shouldCallExecutorShutdown(){
        exceptionDemo.shutdown();
        verify(executor).shutdown();
    }
}
