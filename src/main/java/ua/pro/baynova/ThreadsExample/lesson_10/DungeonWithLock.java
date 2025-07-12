package ua.pro.baynova.ThreadsExample.lesson_10;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DungeonWithLock implements DungeonEntrance{
    private Lock lock = new ReentrantLock();


    @Override
    public void enterAsNPC(String name) {
        lock.lock();
        try {
            System.out.println("🤖 " + name + " (NPC) зашёл в подземелье.");
            performDungeonActions(name, "🤖");
            System.out.println("🤖 " + name + " (NPC) вышел.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void enterAsPlayer(String name) {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    System.out.println("🎮 " + name + " (игрок) зашёл в подземелье.");
                    performDungeonActions(name, "🎮");
                    System.out.println("🎮 " + name + " (игрок) вышел.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("😡 " + name + " (игрок) не стал ждать и ушёл.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void performDungeonActions(String name, String icon) throws InterruptedException {
        System.out.println("   " + name + " " + icon + " исследует подземелье...");
        Thread.sleep(1000);
        System.out.println("   " + name + " " + icon + " сражается с монстром \uD83D\uDC09");
        Thread.sleep(1000);
        System.out.println("   " + name + " " + icon + " находит сокровище \uD83D\uDCB0");
        Thread.sleep(1000);
    }
}
