package ua.pro.baynova.JUnitExamle;

public class DiscountService {
    public double applyDiscount(double price, double discountPercent){
        if (price < 0 || discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Неверные входные данные");
        }
        return price - (price * discountPercent / 100);
    }
}
