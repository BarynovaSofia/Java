package ua.pro.baynova.MockitoExample.lesson_12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

class GiftServiceTest {

    @Mock
    DeliveryService deliveryService;

    @InjectMocks
    GiftService giftService;

    Map<String, String> giftLog = new HashMap<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        doAnswer(invocation -> {
            String name = invocation.getArgument(0);
            String gift = invocation.getArgument(1);

            giftLog.put(name, gift);
            return null;
        }).when(deliveryService).deliver(anyString(), anyString());
    }

    @Test
    void testGiftDeliveryBasedOnPreferences() {
        giftService.sendGift("Марина", "шоколад");
        giftService.sendGift("Олег", "чай");
        giftService.sendGift("Катя", "неизвестно");

        assertEquals("\uD83C\uDF81 Коробка с шоколадом", giftLog.get("Марина"));
        assertEquals("\uD83C\uDF81 Чайный набор", giftLog.get("Олег"));
        assertEquals("\uD83C\uDF81 Подарочный набор-сюрприз", giftLog.get("Катя"));
    }
}
