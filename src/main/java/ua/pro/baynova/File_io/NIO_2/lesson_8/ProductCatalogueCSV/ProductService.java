package ua.pro.baynova.File_io.NIO_2.lesson_8.ProductCatalogueCSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public static List<Product> filterByCategory(List<Product> products, String category) {
        logger.info("-> Фильтрация товаров по категории: {}\n", category);
        List<Product> filtered = products.stream()
                .filter(p -> p.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());

        logger.info("# Найдено {} товаров в категории '{}'\n", filtered.size(), category);
        return filtered;
    }

    public static List<Product> filterByPrice(List<Product> products, double maxPrice) {
        logger.info("# Фильтрация товаров с ценой до {} uah\n", maxPrice);

        List<Product> filtered = products.stream()
                .filter(p -> p.price <= maxPrice)
                .collect(Collectors.toList());

        logger.info("-> Найдено {} товаров\n", filtered.size());
        return filtered;
    }

    public static List<Product> filterByStock(List<Product> products) {
        logger.info("# Фильтрация товаров в наличии\n");

        List<Product> filtered = products.stream()
                .filter(p -> p.inStock)
                .collect(Collectors.toList());

        logger.info("# Найдено {} товаров в наличии\n", filtered.size());
        return filtered;
    }

    public static List<Product> sortByPrice(List<Product> products) {
        logger.info("# Сортировка товаров по цене (дешёвые → дорогие)\n");

        List<Product> sorted = products.stream()
                .sorted(Comparator.comparingDouble(p -> p.price))
                .collect(Collectors.toList());

        logger.info("-> Отсортировано\n");
        return sorted;
    }

    public static List<Product> sortByName(List<Product> products) {
        logger.info("# Сортировка товаров по названию (A-Z)\n");

        List<Product> sorted = products.stream()
                .sorted(Comparator.comparing(p -> p.name))
                .collect(Collectors.toList());

        logger.info("-> Отсортировано\n");
        return sorted;
    }

    public static List<Product> sortByQuantityDescending(List<Product> products) {
        logger.info("№ Сортировка товары по количеству\n");

        List<Product> sorted = products.stream()
                .sorted((p1, p2) -> Integer.compare(p2.quantity, p1.quantity))
                .collect(Collectors.toList());
        logger.info("-> Отсортировано\n");
        return sorted;
    }

    public static Product findByName(List<Product> products, String name) {
        logger.info("# Поиск товара по названию: {}\n", name);

        Product found = products.stream()
                .filter(p -> p.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (found != null) {
            logger.info("-> Найден товар: {}\n", found.name);
        } else {
            logger.info("-> Товар не найден\n");
        }

        return found;
    }

    public static Product findMostExpensive(List<Product> products) {
        logger.info("# Поиск самых дорогих товаров\n");

        Product expensive = products.stream()
                .max(Comparator.comparingDouble(p -> p.price))
                .orElse(null);

        if (expensive != null) {
            logger.info("-> Самый дорогой: {} ({} uah)\n", expensive.name, expensive.price);
        }

        return expensive;
    }

    public static Product findCheapest(List<Product> products) {
        logger.info("# Поиск самых дешёвых товаров\n");

        Product cheap = products.stream()
                .min(Comparator.comparingDouble(p -> p.price))
                .orElse(null);

        if (cheap != null) {
            logger.info("-> Самый дешёвый: {} ({} uah)\n", cheap.name, cheap.price);
        }

        return cheap;
    }

    public static void addProduct(List<Product> products, Product newProduct) {
        logger.info("-> Добавление нового товара: {}\n", newProduct.name);

        products.add(newProduct);
        logger.info("> Товар добавлен в каталог\n");
    }

    public static void deleteProduct(List<Product> products, String productName) {
        logger.info("<- Удаление товара: {}\n", productName);

        boolean removed = products.removeIf(p -> p.name.equalsIgnoreCase(productName));

        if (removed) {
            logger.info("# Товар удалён\n");
        } else {
            logger.info("(!) Товар не найден\n");
        }
    }

    public static void updatePrice(List<Product> products, String productName, double newPrice) {
        logger.info("-> Обновление цены товара: {}\n", productName);

        for (Product p : products) {
            if (p.name.equalsIgnoreCase(productName)) {
                logger.info("  Старая цена: {} uah", p.price);
                p.price = newPrice;
                logger.info("  Новая цена: {} uah\n", newPrice);
                return;
            }
        }

        logger.info("(!) Товар не найден\n");
    }

    public static void generateCategoryReport(List<Product> products) {
        logger.info("-> ОТЧЁТ: Товары по категориям\n");

        var categoryCount = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.counting()
                ));

        for (String category : categoryCount.keySet()) {
            logger.info("  {} : {} товаров", category, categoryCount.get(category));
        }

        logger.info("");
    }

    public static void calculateAveragePrice(List<Product> products) {
        logger.info("-> ОТЧЁТ: Средняя цена товаров\n");

        double average = products.stream()
                .mapToDouble(p -> p.price)
                .average()
                .orElse(0);

        logger.info("Средняя цена: {} uah\n", String.format("%.2f", average));
    }

    public static void calculateTotalValue(List<Product> products) {
        logger.info("-> ОТЧЁТ: Общая стоимость товара на складе\n");

        double totalValue = products.stream()
                .mapToDouble(p -> p.price * p.quantity)
                .sum();

        logger.info("Общая стоимость: {} uah\n", String.format("%.2f", totalValue));
    }

    public static void saveProductsToCSV(List<Product> products, String filePath) {
        logger.info("-> Сохранение товаров в CSV\n");

        try {
            FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withHeader("Name", "Category", "Price", "Quantity", "InStock");
            CSVPrinter printer = new CSVPrinter(writer, csvFormat);

            for (Product p : products) {
                printer.printRecord(p.name, p.category, p.price, p.quantity, p.inStock);
            }

            printer.flush();
            printer.close();
            writer.close();

            logger.info("# Товары сохранены в CSV\n");
        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
    }

    public static List<Product> loadProductsFromCSV(String filePath) {
        logger.info("-> Загрузка из CSV\n");

        List<Product> products = new ArrayList<>();

        try {
            FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8);
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            CSVParser parser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : parser) {
                Product p = new Product();
                p.name = record.get("Name");
                p.category = record.get("Category");
                p.price = Double.parseDouble(record.get("Price"));
                p.quantity = Integer.parseInt(record.get("Quantity"));
                p.inStock = Boolean.parseBoolean(record.get("InStock"));

                products.add(p);
            }

            parser.close();
            reader.close();
            logger.info("# Загружено {} товаров\n", products.size());

        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
        return products;
    }

    public static void displayProducts(List<Product> products) {
        logger.info("-> КАТАЛОГ ТОВАРОВ <-\n");

        for (int i = 0; i < products.size(); i++) {
            logger.info("{}. {}", i + 1, products.get(i));
            logger.info("");

        }
    }
}
