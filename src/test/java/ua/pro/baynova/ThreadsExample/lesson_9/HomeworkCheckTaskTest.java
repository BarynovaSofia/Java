package ua.pro.baynova.ThreadsExample.lesson_9;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomeworkCheckTaskTest {

    @Mock
    private Sleeper sleeper;

    private HomeworkCheckTask task;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        task = new HomeworkCheckTask("Аня", 2, sleeper);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void call_shouldPrintStartAndEndAndSleep() throws Exception {
        task.call();

        verify(sleeper).sleep(2000L);

        String output = outContent.toString();
        assertTrue(output.contains("начал проверку задания от Аня"));
        assertTrue(output.contains("завершил проверку задания от Аня"));
    }

    @Test
    void call_shouldThrowIfInterrupted() throws Exception {
        doThrow(new InterruptedException()).when(sleeper).sleep(anyLong());

        InterruptedException thrown = assertThrows(InterruptedException.class, () -> task.call());

        verify(sleeper).sleep(2000L);

        String output = outContent.toString();
        assertTrue(output.contains("начал проверку задания от Аня"));
        assertTrue(output.contains("прервал проверку задания от Аня"));
    }
}
