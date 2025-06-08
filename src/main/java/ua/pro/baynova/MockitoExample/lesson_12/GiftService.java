package ua.pro.baynova.MockitoExample.lesson_12;

public class GiftService {
    private final DeliveryService deliveryService;

    public GiftService(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    public void sendGift(String name, String preference){
        String gift;
        switch (preference.toLowerCase()){
            case "шоколад":
                gift = "\uD83C\uDF81 Коробка с шоколадом";
                break;
            case "чай":
                gift = "\uD83C\uDF81 Чайный набор";
                break;
            default: gift = "\uD83C\uDF81 Подарочный набор-сюрприз";
        }

        deliveryService.deliver(name, gift);
    }
}
