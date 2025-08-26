package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

public record Order(int id, double amount, String product) {
    @Override
    public String toString() {
        return "Order{id='" + id + "', product='" + product + "', amount=" + amount + "}";
    }
}
