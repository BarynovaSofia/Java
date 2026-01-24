package ua.pro.baynova.File_io.lesson_6.DiscWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DiscWriter {
    private static final Logger logger = LoggerFactory.getLogger(DiscWriter.class);

    public void writerDisc(Disc disc, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(disc);
            logger.info("*** Диск '{}' успешно записан в файл '{}'", disc.getDiscName(), filePath);
        } catch (IOException e) {
            logger.error("!!!!!! Ошибка при записи диска: {}", e.getMessage());
        }
    }
}
