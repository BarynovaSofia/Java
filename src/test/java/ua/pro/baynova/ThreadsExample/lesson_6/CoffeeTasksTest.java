package ua.pro.baynova.ThreadsExample.lesson_6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoffeeTasksTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    void coffeeRunnerTaskShouldIncrementCounterAndPrintMessages(){
        AtomicInteger counter = new AtomicInteger(0);
        CoffeeRunnerTask task = new CoffeeRunnerTask("Лена", counter);

        task.run();

        String output = outContent.toString();

        assertTrue(output.contains("Лена пошёл за кофе"));
        assertTrue(output.contains("Лена вернулся с кофе"));
        assertEquals(1, counter.get());
    }

    @Test
    void coffeePriceFetcherTaskShouldReturnPriceMessage() throws Exception{
        CoffeePriceFetcherTask task = new CoffeePriceFetcherTask("Юля");

        String result = task.call();

        assertTrue(result.startsWith("Юля узнал цену: "));
    }

    @Test
    void officeCoffeeAppIntegrationTest() throws Exception{
        AtomicInteger count = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new CoffeeRunnerTask("Саша", count));
        executor.submit(new CoffeeRunnerTask("Лена", count));
        executor.submit(new CoffeeRunnerTask("Дима", count));

        Future<String> result1 = executor.submit(new CoffeePriceFetcherTask("Юля"));
        Future<String> result2 = executor.submit(new CoffeePriceFetcherTask("Артём"));

        String value1 = result1.get();
        String value2 = result2.get();

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(3, count.get());
        assertTrue(value1.contains("Юля узнал цену:"));
        assertTrue(value2.contains("Артём узнал цену:"));

        String output = outContent.toString();

        assertTrue(output.contains("Саша пошёл за кофе"));
        assertTrue(output.contains("Лена пошёл за кофе"));
        assertTrue(output.contains("Дима пошёл за кофе"));
    }

    @Test
    void coffeeRunnerTaskCanBeMockedAndVerified(){
        AtomicInteger counter = mock(AtomicInteger.class);
        CoffeeRunnerTask task = new CoffeeRunnerTask("Олег", counter);

        task.run();

        verify(counter, times(1)).incrementAndGet();
    }
}
