package ua.pro.baynova.File_io.NIO_2.lesson_8.DatabaseStaff_CSV;

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(CsvEmployeeService.class);

    public static void saveEmployeesToCSV(List<Employee> employees, String filePath) {
        logger.info("-> ЭКСПОРТ СОТРУДНИКОВ В CSV <-\n");
        logger.info("# Файл: {}", filePath);
        logger.info("> Сотрудников: {}\n", employees.size());

        try {
            FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            logger.debug("# FileWriter открыт");

            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withHeader("Name", "Position", "Department", "Salary",
                            "Years of Service", "Email", "Phone", "Hire Date", "Is Active");
            logger.debug("# CSV формат создан с заголовками");

            CSVPrinter printer = new CSVPrinter(writer, csvFormat);
            logger.debug("# CSVPrinter создан");

            for (Employee emp : employees) {
                printer.printRecord(
                        emp.name,
                        emp.position,
                        emp.department,
                        emp.salary,
                        emp.yearsOfService,
                        emp.email,
                        emp.phone,
                        emp.hireDate,
                        emp.isActive
                );
            }
            logger.debug("# Все сотрудники записаны");
            printer.flush();
            printer.close();
            writer.close();
            logger.info("-> Сотрудники успешно экспортированы в CSV!\n");

            showCSVContent(filePath);
        } catch (IOException e) {
            logger.error("(!) Ошибка при сохранении: {}", e.getMessage());
        }
    }

    public static List<Employee> loadEmployeesFromCSV(String filePath) {
        logger.info("-> ЗАГРУЗКА СОТРУДНИКОВ ИЗ CSV <-\n");
        logger.info("# Файл: {}\n", filePath);

        List<Employee> employees = new ArrayList<>();

        try {
            FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8);
            logger.debug("# FileReader открыт");

            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            logger.debug("# CSV формат создан");

            CSVParser parser = new CSVParser(reader, csvFormat);
            logger.debug("# CSVParser создан");

            int rowCount = 0;
            for (CSVRecord record : parser) {
                Employee emp = new Employee();
                emp.name = record.get("Name");
                emp.position = record.get("Position");
                emp.department = record.get("Department");
                emp.salary = Double.parseDouble(record.get("Salary"));
                emp.yearsOfService = Integer.parseInt(record.get("Years of Service"));
                emp.email = record.get("Email");
                emp.phone = record.get("Phone");
                emp.hireDate = record.get("Hire Date");
                emp.isActive = Boolean.parseBoolean(record.get("Is Active"));

                employees.add(emp);
                rowCount++;
                logger.debug("# Загружен сотрудник: {}", emp.name);
            }

            parser.close();
            reader.close();

            logger.info("-> Загружено {} сотрудников\n", rowCount);
            return employees;

        } catch (IOException e) {
            logger.error("(!) Ошибка при загрузке: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void showCSVContent(String filePath) {
        logger.info("# Содержимое CSV файла:\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

            String[] lines = content.split("\n");
            for (int i = 0; i < Math.min(lines.length, 5); i++) {
                logger.info(lines[i]);
            }
            if (lines.length > 5) {
                logger.info("... (ещё {} строк)", lines.length - 5);
            }
            logger.info("");

        } catch (IOException e) {
            logger.error("(!) Ошибка: {}", e.getMessage());
        }
    }

    public static void displayEmployees(List<Employee> employees) {
        logger.info("-> ВСЕ СОТРУДНИКИ <-\n");
        for (int i = 0; i < employees.size(); i++) {
            logger.info("{}. {}", i + 1, employees.get(i));
            logger.info("");
        }
    }

    public static void showDepartmentStatistics(List<Employee> employees) {
        logger.info("-> СТАТИСТИКА ПО ОТДЕЛАМ <-\n");

        java.util.Map<String, List<Employee>> byDept = new java.util.HashMap<>();

        for (Employee emp : employees) {
            byDept.computeIfAbsent(emp.department, k -> new ArrayList<>()).add(emp);
        }

        for (String dept : byDept.keySet()) {
            List<Employee> deptEmployees = byDept.get(dept);
            double avgSalary = deptEmployees.stream()
                    .mapToDouble(e -> e.salary)
                    .average()
                    .orElse(0);

            logger.info("# {}: {} сотрудников, средняя зарплата: {}",
                    dept, deptEmployees.size(), String.format("%.2f", avgSalary));
        }
        logger.info("");
    }
}
