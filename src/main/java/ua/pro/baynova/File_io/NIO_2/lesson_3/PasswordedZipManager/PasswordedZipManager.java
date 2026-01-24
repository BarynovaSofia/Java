package ua.pro.baynova.File_io.NIO_2.lesson_3.PasswordedZipManager;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class PasswordedZipManager {
    private static final Logger logger = LoggerFactory.getLogger(PasswordedZipManager.class);

    public static void createPasswordedArchive(String sourceFolder, String zipPath, String password) {
        logger.info("> СОЗДАНИЕ АРХИВА С ПАРОЛЕМ <\n");
        logger.info("# Исходная папка: {}", sourceFolder);
        logger.info("-> Путь архива: {}", zipPath);
        logger.info("[*] Пароль: {'*'.repeat(password.length())}\n");

        File sourceDir = new File(sourceFolder);
        if (!sourceDir.exists()) {
            logger.error("(!) Папка не найдена: {}", sourceFolder);
            return;
        }

        try {
            ZipFile zipFile = new ZipFile(zipPath);
            logger.debug("# ZipFile объект создан");

            zipFile.setPassword(password.toCharArray());
            logger.debug("✓ Пароль установлен");
            ZipParameters parameters = new ZipParameters();

            parameters.setEncryptFiles(true);
            logger.debug("# Шифрование включено");

            parameters.setEncryptionMethod(EncryptionMethod.AES);
            logger.debug("# Метод шифрования: AES");

            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
            logger.debug("# Уровень ключа: AES-256\n");

            File[] files = sourceDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        logger.info("-> Добавление файла: {}", file.getName());
                        zipFile.addFile(file, parameters);
                        logger.debug("# Файл добавлен и зашифрован");
                    }
                }
            }

            logger.info("# Архив с паролем создан!");
            logger.info("-> Архив: {}\n", zipPath);

        } catch (ZipException e) {
            logger.error("(!) Ошибка при создании архива: {}", e.getMessage());
        }
    }

    public static void extractPasswordedArchive(String zipPath, String outputFolder, String password) {
        logger.info("<< РАСПАКОВКА АРХИВА С ПАРОЛЕМ >>\n");
        logger.info("# Архив: {}", zipPath);
        logger.info("-> Папка назначения: {}", outputFolder);
        logger.info("[*] Пароль: {'*'.repeat(password.length())}\n");

        try {
            ZipFile zipFile = new ZipFile(zipPath);
            logger.debug("# ZipFile открыт для чтения");

            if (zipFile.isEncrypted()) {
                logger.info("[^] Архив защищён паролем!");
                zipFile.setPassword(password.toCharArray());
                logger.debug("# Пароль установлен для распаковки");
            }

            if (zipFile.getFileHeaders().isEmpty()) {
                logger.error("(!) Архив пуст!");
                return;
            }
            logger.debug("# Архив содержит файлы, начинается распаковку\n");
            zipFile.extractAll(outputFolder);
            logger.info("# Начало распаковки...");

            int fileCount = zipFile.getFileHeaders().size();
            logger.info("# Распаковка завершена!");
            logger.info("# Распаковано файлов: {}\n", fileCount);

        } catch (ZipException e) {
            if (e.getMessage().contains("password")) {
                logger.error("[!] ОШИБКА: Пароль неправильный!");
            } else {
                logger.error("(!) Ошибка при распаковке: {}", e.getMessage());
            }
        }
    }

    public static boolean verifyPassword(String zipPath, String password) {
        logger.info("> ПРОВЕРКА ПАРОЛЯ <\n");
        logger.info("# Архив: {}", zipPath);

        try {
            ZipFile zipFile = new ZipFile(zipPath);
            if (!zipFile.isEncrypted()) {
                logger.info("️[!] Архив НЕ защищён паролем\n");
                return true;
            }
            zipFile.setPassword(password.toCharArray());

            try {
                zipFile.getFileHeaders();
                logger.info("[*] Пароль ПРАВИЛЬНЫЙ!\n");
                return true;
            } catch (ZipException e) {
                if (e.getMessage().contains("password") || e.getMessage().contains("encryption")) {
                    logger.error("[!] Пароль НЕПРАВИЛЬНЫЙ!\n");
                } else {
                    logger.error("(!) Ошибка: {}\n", e.getMessage());
                }
                return false;
            }

        } catch (ZipException e) {
            logger.error("(!) Ошибка при открытии архива: {}\n", e.getMessage());
            return false;
        }
    }

    public static void showArchiveInfo(String zipPath) {
        logger.info("> ИНФОРМАЦИЯ ОБ АРХИВЕ <\n");
        logger.info("# Архив: {}\n", zipPath);

        try {
            ZipFile zipFile = new ZipFile(zipPath);

            if (zipFile.isEncrypted()) {
                logger.info("[^] Статус: ЗАЩИЩЁН ПАРОЛЕМ");
                logger.info("Метод: AES-256");
            } else {
                logger.info("[] Статус: БЕЗ ПАРОЛЯ");
            }

            int fileCount = zipFile.getFileHeaders().size();
            logger.info("# Файлов в архиве: {}", fileCount);

            File archiveFile = new File(zipPath);
            long size = archiveFile.length();
            logger.info("# Размер архива: {} байт ({} КБ)\n", size, size / 1024);

        } catch (ZipException e) {
            logger.error("(!) Ошибка при чтении информации: {}", e.getMessage());
        }
    }
}
