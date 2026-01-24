package ua.pro.baynova.File_io.NIO_2.lesson_8.DatabaseStaff_CSV;

public class Employee {
    public String name;
    public String position;
    public String department;
    public double salary;
    public int yearsOfService;
    public String email;
    public String phone;
    public String hireDate;
    public boolean isActive;

    public Employee() {
    }

    public Employee(String name, String position, String department,
                    double salary, int yearsOfService, String email,
                    String phone, String hireDate, boolean isActive) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "# " + name + "\n" +
                " >  Должность: " + position + " (" + department + ")\n" +
                " >  Зарплата: " + salary + " грн\n" +
                " >  Стаж: " + yearsOfService + " лет\n" +
                " >  Email: " + email + "\n" +
                " > Телефон: " + phone + "\n" +
                " >  Дата приёма: " + hireDate + "\n" +
                " > Активен: " + (isActive ? "Да" : "Нет");
    }
}
