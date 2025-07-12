package ua.pro.baynova.ThreadsExample.lesson_11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private final ExecutorService executor;
    private final ServerFlag flag;

    public GameServer(ExecutorService executor, ServerFlag flag) {
        this.executor = executor;
        this.flag = flag;
    }

    public void startGame() throws InterruptedException{
        executor.submit(new GameLoopTask("Игрок-1", flag));
        executor.submit(new GameLoopTask("Игрок-2", flag));
        executor.submit(new GameLoopTask("Игрок-3", flag));

        Thread.sleep(5000);
        if (flag instanceof AtomicServerFlag atomicFlag) {
            atomicFlag.stop();
        }
        System.out.println("🛑 Сервер выключается...");

        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("✅ Все потоки завершены.");
    }
}
