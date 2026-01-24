package ua.pro.baynova.File_io.NIO_2.lesson_8.ProductCatalogueCSV;

public class Product {
    public String name;
    public String category;
    public double price;
    public int quantity;
    public boolean inStock;

    public Product() {
    }

    public Product(String name, String category, double price, int quantity, boolean inStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "# " + name + "\n" +
                "   Категория: " + category + "\n" +
                "   Цена: " + price + " uah\n" +
                "   На складе: " + quantity + " шт\n" +
                "   В наличии: " + (inStock ? "ДА" : "НЕТ");
    }
}
