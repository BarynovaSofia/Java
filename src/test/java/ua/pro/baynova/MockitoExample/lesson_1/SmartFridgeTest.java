package ua.pro.baynova.MockitoExample.lesson_1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SmartFridgeTest {

    @Test
    public void testProductAlreadyExists(){
        OnlineStore mockStore = mock(OnlineStore.class);

        SmartFridge fridge = new SmartFridge(Arrays.asList("milk", "eggs") , mockStore);

        boolean result = fridge.checkAndOrder("milk");

        assertTrue(result);
    }

    @Test
    public void testProductIsMissingAndGetsOrdered(){
        OnlineStore mockStore = mock(OnlineStore.class);

        when(mockStore.orderProduct("cheese")).thenReturn(true);

        SmartFridge fridge = new SmartFridge(Arrays.asList("milk", "eggs"), mockStore);

        boolean result = fridge.checkAndOrder("cheese");

        assertTrue(result);

        verify(mockStore).orderProduct("cheese");
    }
}