package ua.pro.baynova.File_io.NIO_2.lesson_5.TemporaryReportManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("\n>>> ДЕМОНСТРАЦИЯ ВРЕМЕННЫХ ФАЙЛОВ И ДИРЕКТОРИЙ <<<\n");
        logger.info("# СЦЕНАРИЙ 1: Один временный отчёт #\n");

        List<String> salesData = new ArrayList<>();
        salesData.add("Продажи за январь: 150,000");
        salesData.add("Продажи за февраль: 180,000");
        salesData.add("Продажи за март: 165,000");
        salesData.add("Всего: 495,000");

        Path tempReport1 = TemporaryReportManager.createTemporaryReport("Sales_Q1", salesData);

        if (tempReport1 != null) {
            TemporaryReportManager.showFileInfo(tempReport1);
            TemporaryReportManager.readTemporaryReport(tempReport1);
            TemporaryReportManager.deleteTemporaryFile(tempReport1);
        }

        logger.info("\n# СЦЕНАРИЙ 2: Временная директория с несколькими отчётами #\n");
        Path tempDir = TemporaryReportManager.createTemporaryDirectory("MonthlySales");

        if (tempDir != null) {
            List<String> report1Data = new ArrayList<>();
            report1Data.add("Отдел: Продажи");
            report1Data.add("Показатель: 150,000");
            report1Data.add("Статус: # Норма");
            TemporaryReportManager.saveReportToDirectory(tempDir, "January", report1Data);

            List<String> report2Data = new ArrayList<>();
            report2Data.add("Отдел: IT");
            report2Data.add("Показатель: 95% готовности систем");
            report2Data.add("Статус: # Хорошо");
            TemporaryReportManager.saveReportToDirectory(tempDir, "February", report2Data);

            List<String> report3Data = new ArrayList<>();
            report3Data.add("Отдел: HR");
            report3Data.add("Показатель: 5 новых сотрудников");
            report3Data.add("Статус: # Плановые нанятия");
            TemporaryReportManager.saveReportToDirectory(tempDir, "March", report3Data);

            logger.info("\n");

            TemporaryReportManager.listTemporaryReports(tempDir);
            TemporaryReportManager.deleteTemporaryDirectory(tempDir);
        }

        logger.info("-> Демонстрация завершена!\n");
    }
}
