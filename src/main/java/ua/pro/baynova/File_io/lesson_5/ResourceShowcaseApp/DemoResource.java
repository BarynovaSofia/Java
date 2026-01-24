package ua.pro.baynova.File_io.lesson_5.ResourceShowcaseApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoResource implements AutoCloseable {
    private static final Logger loger = LoggerFactory.getLogger(DemoResource.class);
    private final String name;

    public DemoResource(String name) {
        this.name = name;
        loger.info("✅ Ресурс '{}' создан и готов к использованию", name);
    }

    public void doWork() {
        loger.info("\uD83D\uDCA1 Ресурс '{}' выполняет работу", name);
    }

    @Override
    public void close() {
        loger.info("\uD83D\uDD12 Ресурс '{}' закрыт автоматически" + name);
    }
}
