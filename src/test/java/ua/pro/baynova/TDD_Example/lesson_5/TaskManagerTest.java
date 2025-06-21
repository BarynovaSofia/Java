package ua.pro.baynova.TDD_Example.lesson_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskManagerTest {

    @Mock
    NotificationService notificationService;

    @Spy
    LoggerService loggerService;

    @InjectMocks
    TaskManager taskManager;

    @Captor
    ArgumentCaptor<Task> tackCaptor;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotifyImportantTask(){
        Task importantTask = new Task("10", "Urgent bug fix", true);
        Task regularTask = new Task("11", "Refactor code", false);

        taskManager.addTask(importantTask);
        taskManager.addTask(regularTask);

        verify(notificationService).notifyImportant(tackCaptor.capture());

        Task captured = tackCaptor.getValue();

        assertEquals("Urgent bug fix", captured.getDescription());
        assertTrue(captured.isImportant());

        verify(notificationService, never()).notifyImportant(regularTask);
    }

    @Test
    void testCannotAddMoreThanFiveActiveTasks(){
        for (int i = 1; i <= 5 ; i++) {
            taskManager.addTask(new Task(String.valueOf(i), "Task " + i, false));
        }

        Task sixthTask = new Task("6", "Overflow task", false);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            taskManager.addTask(sixthTask);
        });

        assertEquals("Too many active tasks", exception.getMessage());
    }

    @Test
    void testGetImportantTasks(){
        Task task1 = new Task("1", " ", false);
        Task task2 = new Task("2", " ", true);
        Task task3 = new Task("3", " ", true);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        List<Task> important = taskManager.getImportantTasks();

        assertEquals(2, important.size());
        assertTrue(important.contains(task2));
        assertTrue(important.contains(task3));
        assertFalse(important.contains(task1));
    }

    @Test
    void testGetTaskById(){
        Task task1 = new Task("1", "Do laundry", false);
        Task task2 = new Task("2", "Buy groceries", true);

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Task found = taskManager.getTaskById("2");

        assertNotNull(found);
        assertEquals("Buy groceries", found.getDescription());
        assertNull(taskManager.getTaskById("999"));
    }

    @Test
    void testCompleteTask(){
        Task task = new Task("2", "Write tests", false);

        taskManager.addTask(task);
        taskManager.completeTask("2");

        List<Task> active = taskManager.getActiveTasks();
        assertTrue(active.isEmpty());

        List<Task> completed = taskManager.getCompletedTasks();
        assertEquals(1, completed.size());
        assertEquals("Write tests", completed.get(0).getDescription());
    }

    @Test
    void testRemoveTaskById(){
        Task task1 = new Task("1", "Clean room", false);
        Task task2 = new Task("2", "Write report", true);

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.removeTask("1");

        List<Task> activeTasks = taskManager.getActiveTasks();
        assertEquals(1, activeTasks.size());
        assertEquals("2", activeTasks.get(0).getId());
    }

    @Test
    void testLoggerIsCalledWhenTaskAdded(){
        Task task = new Task("123", "Log this task", false);

        taskManager.addTask(task);

        verify(loggerService).log("Added task: Log this task");
    }
}
