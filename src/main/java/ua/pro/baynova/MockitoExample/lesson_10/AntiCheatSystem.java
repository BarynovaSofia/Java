package ua.pro.baynova.MockitoExample.lesson_10;

public class AntiCheatSystem {
    private final CheatDetector cheatDetector;
    private final BanService banService;
    private final NotificationService notificationService;

    public AntiCheatSystem(CheatDetector cheatDetector, BanService banService, NotificationService notificationService){
        this.cheatDetector = cheatDetector;
        this.banService = banService;
        this.notificationService = notificationService;
    }

    public void checkPlayer(String playerId){

        try {

            if (cheatDetector.hasCheats(playerId)) {
                banService.ban(playerId);
                notificationService.notify(playerId, "Вы забанены за использование читов.");
            } else {
                notificationService.notify(playerId, "Добро пожаловать в игру!");
            }
        } catch (RuntimeException e){
            notificationService.notify(playerId, "Не удалось проверить ваш аккаунт. Повторите попытку позже.");
        }
    }
}
