package ua.pro.baynova.ThreadsExample.lesson_7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.concurrent.ScheduledExecutorService;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostureReminderTaskTest {

    @Mock
    ScheduledExecutorService scheduler;

    @InjectMocks
    PostureReminderTask task;

    @Test
    void run_shouldIncrementCountAndShutdownAfterThreeRuns() {

        when(scheduler.isShutdown()).thenReturn(false);

        task.run();
        verify(scheduler, never()).shutdown();

        task.run();
        verify(scheduler, never()).shutdown();

        task.run();
        verify(scheduler, times(1)).shutdown();

        when(scheduler.isShutdown()).thenReturn(true);

        task.run();
        verify(scheduler, times(1)).shutdown();
    }
}
