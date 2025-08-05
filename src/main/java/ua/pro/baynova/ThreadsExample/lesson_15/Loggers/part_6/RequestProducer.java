package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

import java.util.Random;
import java.util.logging.Logger;

public class RequestProducer {
    private static final Logger logger = Logger.getLogger(RequestProducer.class.getName());
    private final Random random = new Random();

    public Request produce(){
        int id = random.nextInt(100);
        String data = random.nextBoolean() ? "ClientData" + id : null;
        Request request = new Request(id, data);

        logger.info("Создана заявка: " + request);
        return request;
    }
}
