package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_2;

import java.util.Random;

public class CandyWorker implements Runnable {
    private final String name;
    private final SimpleLogger logger;
    private final Random random = new Random();

    public CandyWorker(String name, SimpleLogger logger){
        this.name = name;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.info(name + ": Начинает упаковку конфеты");
        logger.debug(name + ": Проверяет машину...");

        try {
            Thread.sleep(random.nextInt(1000));

            int event = random.nextInt(10);

            if (event < 6){
                logger.info(name + ": Упаковал конфету успешно 🍬");
            } else if (event < 8) {
                logger.warn(name + ": Обёртка немного порвана ⚠️");
            } else if (event < 9) {
                logger.error(name + ": Ошибка упаковки ❌");
            } else {
                logger.fatal(name + ": Заводской пожар! 🔥 Срочно эвакуация!");
            }
        } catch (InterruptedException e){
            logger.error(name + ": Работа прервана");
            Thread.currentThread().interrupt();
        }
    }
}
