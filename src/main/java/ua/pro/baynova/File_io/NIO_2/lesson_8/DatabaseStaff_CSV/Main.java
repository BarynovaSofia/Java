package ua.pro.baynova.File_io.NIO_2.lesson_8.DatabaseStaff_CSV;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\Dell\\IdeaProjects\\Java\\employees.csv";

        logger.info("\n# CSV -> БАЗА ДАННЫХ СОТРУДНИКОВ\n");
        logger.info("Экспортируем в CSV и откроем в Excel!\n");


        logger.info("-> СЦЕНАРИЙ 1: Создание базы данных сотрудников <-\n");
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                "Иван Петров",
                "Senior Developer",
                "IT",
                150000,
                5,
                "ivan.petrov@company.com",
                "+380 (995) 111-11-11",
                "2019-03-15",
                true
        ));

        employees.add(new Employee(
                "Мария Сидорова",
                "Junior Developer",
                "IT",
                80000,
                1,
                "maria.sidorova@company.com",
                "+380 (995) 111-11-11",
                "2023-09-01",
                true
        ));

        employees.add(new Employee(
                "Алексей Иванов",
                "DevOps Engineer",
                "IT",
                120000,
                3,
                "alexey.ivanov@company.com",
                "+380 (995) 111-11-11",
                "2021-06-10",
                true
        ));

        employees.add(new Employee(
                "Анна Волкова",
                "HR Manager",
                "HR",
                95000,
                4,
                "anna.volkova@company.com",
                "+380 (995) 111-11-11",
                "2020-01-20",
                true
        ));

        employees.add(new Employee(
                "Ольга Морозова",
                "Recruiter",
                "HR",
                70000,
                2,
                "olga.morozova@company.com",
                "+380 (995) 111-11-11",
                "2022-05-15",
                true
        ));

        employees.add(new Employee(
                "Сергей Борисов",
                "Sales Manager",
                "Sales",
                110000,
                6,
                "sergey.borisov@company.com",
                "+380 (995) 111-11-11",
                "2018-11-01",
                true
        ));

        employees.add(new Employee(
                "Елена Соколова",
                "Sales Representative",
                "Sales",
                75000,
                2,
                "elena.sokolova@company.com",
                "+380 (995) 111-11-11",
                "2022-08-10",
                false
        ));

        employees.add(new Employee(
                "Дмитрий Федоров",
                "CFO",
                "Finance",
                180000,
                10,
                "dmitry.fedorov@company.com",
                "+380 (995) 111-11-11",
                "2014-02-01",
                true
        ));

        employees.add(new Employee(
                "Виктория Николаева",
                "Accountant",
                "Finance",
                65000,
                1,
                "viktoria.nikolaeva@company.com",
                "+380 (995) 111-11-11",
                "2024-01-15",
                true
        ));

        logger.info("# База данных с {} сотрудниками\n", employees.size());


        logger.info("-> СЦЕНАРИЙ 2: Информация о сотрудниках <-\n");
        CsvEmployeeService.displayEmployees(employees);

        logger.info("-> СЦЕНАРИЙ 3: Экспорт в CSV <-\n");
        CsvEmployeeService.saveEmployeesToCSV(employees, csvFilePath);

        logger.info("-> СЦЕНАРИЙ 4: Загрузка из CSV <-\n");
        List<Employee> loadedEmployees = CsvEmployeeService.loadEmployeesFromCSV(csvFilePath);
        CsvEmployeeService.displayEmployees(loadedEmployees);

    }
}
