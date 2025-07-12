package ua.pro.baynova.ThreadsExample.lesson_11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameApp {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ServerFlag flag = new AtomicServerFlag();
        GameServer server = new GameServer(executor, flag);
        server.startGame();
    }
}
