package ua.pro.baynova.MockitoExample.lesson_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KettleServiceTest {

    @Test
    public void testMakeTea(){
        Kettle mockKettle = mock(Kettle.class);

        KettleService service = new KettleService(mockKettle);

        service.makeTea();

        verify(mockKettle).boil();
        verify(mockKettle).pour("кружка");
        verify(mockKettle, timeout(1)).boil();

        verifyNoMoreInteractions(mockKettle);
    }
}