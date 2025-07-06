package ua.pro.baynova.ThreadsExample.lesson_8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportPrinterTest {

    @Mock
    private ExecutorService executor;

    @Mock
    private Future<?> future;

    private ReportPrinter reportPrinter;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportPrinter = new ReportPrinter(executor);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    @Test
    void printReports_shouldExecuteTasksAndCatchSubmitException() throws Exception{
        doAnswer(invocation ->{
            Runnable task = invocation.getArgument(0);
            try {
                task.run();
            } catch (RuntimeException ignored){}
            return null;
        }).when(executor).execute(any(Runnable.class));

        when(executor.submit(any(Runnable.class))).thenAnswer(invocation ->{
            Runnable task = invocation.getArgument(0);
            try {
                task.run();
            } catch (RuntimeException ignored){}
            return future;
        });

        when(future.get()).thenThrow(new ExecutionException(new RuntimeException("Ошибка при печати через submit")));

        reportPrinter.printReports();

        verify(executor).execute(any(Runnable.class));
        verify(executor).submit(any(Runnable.class));

        String output = outContent.toString();

        assertTrue(output.contains("🖨️ execute(): Печатаю отчёт..."));
        assertTrue(output.contains("🖨️ submit(): Печатаю отчёт..."));
        assertTrue(output.contains("❗ Поймали ошибку из submit(): Ошибка при печати через submit"));
    }

    @Test
    void shutdown_shouldCallExecutorShutdown(){
        reportPrinter.shutdown();
        verify(executor).shutdown();
    }
}
