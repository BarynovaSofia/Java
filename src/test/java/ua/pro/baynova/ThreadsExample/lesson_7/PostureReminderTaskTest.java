package ua.pro.baynova.ThreadsExample.lesson_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ScheduledExecutorService;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostureReminderTaskTest {

    @Mock
    ScheduledExecutorService scheduler;

    @InjectMocks
    PostureReminderTask task;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    @Test
    void fullReminderFlow_shouldPrintMessagesAndShutdownCorrectly() {

        when(scheduler.isShutdown()).thenReturn(false);

        task.run(); // count = 1
        task.run(); // count = 2
        task.run(); // count = 3

        verify(scheduler, times(1)).shutdown();

        String output = outContent.toString();

        assertTrue(output.contains("🔔 Напоминание #1: Выпрями спину!"));
        assertTrue(output.contains("🔔 Напоминание #2: Выпрями спину!"));
        assertTrue(output.contains("🔔 Напоминание #3: Выпрями спину!"));
        assertTrue(output.contains("😌 Ок, больше не напоминаю. Но спину всё равно держи прямо!"));
    }
}
