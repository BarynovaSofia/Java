package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DiscReader {
    private static final Logger logger = LoggerFactory.getLogger(DiscReader.class);

    public Disc readerDisc(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Disc disc = (Disc) ois.readObject();
            logger.info("() Диск '{}' успешно загружен из '{}'", disc.getDiscName(), filePath);
            return disc;

        } catch (IOException | ClassNotFoundException e) {
            logger.error("!!!!! Ошибка при чтении диска: {}", e.getMessage());
            return null;
        }
    }
}
