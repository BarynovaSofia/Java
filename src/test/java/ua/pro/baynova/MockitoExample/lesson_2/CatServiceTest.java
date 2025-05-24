package ua.pro.baynova.MockitoExample.lesson_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatServiceTest {

    @Test
    public void testMakeCatSpeak() {
        Cat mockCat = mock(Cat.class);

        when(mockCat.speak()).thenReturn("Meow!");

        CatService catService = new CatService(mockCat);

        assertEquals("Meow!", catService.makeCatSpeak());
    }
}
