package ua.pro.baynova.File_io.NIO_2.lesson_8.ProductCatalogueCSV;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String csvPath = "C:\\Users\\Dell\\IdeaProjects\\Java\\products.csv";

        logger.info("\n-> МАГАЗИН ЭЛЕКТРОНИКИ\n");
        logger.info("Демонстрация ФИЛЬТРАЦИИ, СОРТИРОВКИ, ПОИСКА, РЕДАКТИРОВАНИЯ, ОТЧЁТОВ\n");

        logger.info("ШАГ 1: Каталог товаров\n");
        List<Product> products = new ArrayList<>();

        products.add(new Product("Ноутбук ASUS", "Электроника", 30000, 5, true));
        products.add(new Product("iPhone 15", "Электроника", 70000, 3, true));
        products.add(new Product("Телевизор LG", "Электроника", 25000, 2, true));
        products.add(new Product("Наушники Sony", "Электроника", 2000, 15, true));
        products.add(new Product("iPad Pro", "Электроника", 55000, 0, false));

        products.add(new Product("Куртка Nike", "Одежда", 7000, 20, true));
        products.add(new Product("Джинсы Levi's", "Одежда", 5000, 30, true));
        products.add(new Product("Рубашка Calvin Klein", "Одежда", 3500, 10, true));

        products.add(new Product("Стол деревянный", "Мебель", 6000, 4, true));
        products.add(new Product("Стул офисный", "Мебель", 4500, 8, true));

        logger.info("# Создано {} товаров\n", products.size());

        logger.info("-> Весь каталог товаров ->\n");
        ProductService.displayProducts(products);

        logger.info("ШАГ 2: ФИЛЬТРАЦИЯ\n");
        logger.info("-> ФИЛЬТР 1: Только товары из категории 'Электроника' <-\n");
        List<Product> electronics = ProductService.filterByCategory(products, "");
        ProductService.displayProducts(electronics);

        logger.info("-> ФИЛЬТР 2: Только товары дешевле 10000 uah <-\n");
        List<Product> cheap = ProductService.filterByPrice(products, 10000);
        ProductService.displayProducts(cheap);

        logger.info("-> ФИЛЬТР 3: Только товары в наличии <-\n");
        List<Product> inStock = ProductService.filterByStock(products);
        ProductService.displayProducts(inStock);

        logger.info("ШАГ 3: СОРТИРОВКА\n");
        logger.info("-> СОРТИРОВКА 1: По цене <-\n");
        List<Product> byPrice = ProductService.sortByPrice(products);
        ProductService.displayProducts(byPrice);

        logger.info("-> СОРТИРОВКА 2: По названию <-\n");
        List<Product> byName = ProductService.sortByName(products);
        ProductService.displayProducts(byName);

        logger.info("-> СОРТИРОВКА 3: По количеству <-\n");
        List<Product> byQuantity = ProductService.sortByQuantityDescending(products);
        ProductService.displayProducts(byQuantity);

        logger.info("ШАГ 4: ПОИСК\n");
        logger.info("-> ПОИСК 1: Найти товар 'iPhone 15' <-\n");
        Product found = ProductService.findByName(products, "iPhone 15");
        if (found != null) {
            logger.info("{}\n", found);
        }

        logger.info("-> ПОИСК 2: Самый дорогой товар в магазине <-\n");
        ProductService.findMostExpensive(products);

        logger.info("-> ПОИСК 3: Самый дешёвый товар в магазине <-\n");
        ProductService.findCheapest(products);

        logger.info("ШАГ 5: РЕДАКТИРОВАНИЕ\n");
        logger.info("-> РЕДАКТИРОВАНИЕ 1: (Добавить новый товар) <-\n");
        Product newProduct = new Product("Смарт-часы Apple Watch", "Электроника", 35000, 12, true);
        ProductService.addProduct(products, newProduct);

        logger.info("-> РЕДАКТИРОВАНИЕ 2: Обновить цену товара <-\n");
        logger.info("Чёрная пятница!\n\n");
        ProductService.updatePrice(products, "iPhone 15", 50000);

        logger.info("-> РЕДАКТИРОВАНИЕ 3: Удалить товар <-\n");
        logger.info("iPad Pro нет в наличии\n\n");
        ProductService.deleteProduct(products, "iPad Pro");

        logger.info("ШАГ 6: ОТЧЁТЫ (статистика и аналитика)\n");
        ProductService.generateCategoryReport(products);
        ProductService.calculateAveragePrice(products);
        ProductService.calculateTotalValue(products);

        logger.info("ШАГ 7: Сохранение товаров в CSV\n");
        ProductService.saveProductsToCSV(products, csvPath);
        logger.info("-> CSV файл сохранён: {}\n", csvPath);
    }
}
