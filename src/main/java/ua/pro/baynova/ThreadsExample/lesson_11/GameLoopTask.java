package ua.pro.baynova.ThreadsExample.lesson_11;

//Задача, которая выполняется потоком (игровая логика)
public class GameLoopTask implements Runnable {
    private final String playerName;
    private final ServerFlag serverFlag;

    public GameLoopTask(String playerName, ServerFlag serverFlag){
        this.playerName = playerName;
        this.serverFlag = serverFlag;
    }

    @Override
    public void run(){
        while (serverFlag.isRunning()){
            System.out.println("🎮 " + playerName + " делает свой ход...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("🔌 " + playerName + " завершил игру (сервер выключен).");
    }
}
