package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

public class RequestProcessingService {
    private final RequestProducer producer = new RequestProducer();
    private final RequestHandler handler = new RequestHandler();
    private final RequestValidator validator = new RequestValidator();

    public void process(){
        Request request = producer.produce();

        if (validator.isValid(request)){
            handler.handle(request);
        } else {
            System.out.println("Заявка отклонена: " + request);
        }
    }
}
