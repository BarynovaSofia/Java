package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {
    DiscountService discount = new DiscountService();

    @Test
    void testApplyDiscountRegular() {
        double result = discount.applyDiscount(200.0, 10.0);
        assertEquals(180.0, result);
    }

    @Test
    void testApplyZeroDiscount(){
        double result = discount.applyDiscount(150.0, 0.0);
        assertEquals(150.0, result);
    }

    @Test
    void testApplyFullDiscount(){
        double result = discount.applyDiscount(100.0, 100.0);
        assertEquals(0.0, result);
    }

    @Test
    void testNegativePriceThrowsException(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            discount.applyDiscount(-50.0, 10.0);
        });
        assertEquals("Неверные входные данные" ,ex.getMessage());
    }

    @Test
    void testTooBigDiscountThrowsException(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            discount.applyDiscount(100.0, 130.0);
        });
        assertEquals("Неверные входные данные" ,ex.getMessage());
    }
}