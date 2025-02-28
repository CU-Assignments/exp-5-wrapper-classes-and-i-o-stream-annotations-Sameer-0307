package DSA;

import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String id; 
    String name, designation;
    double salary;

    Employee(String id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeMenuApp {
    private static final String FILE_NAME = "employees.ser";
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployees();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Employee  2. Display All  3. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Handle non-integer input safely
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addEmployee(scanner);
                case 2 -> displayEmployees();
                case 3 -> {
                    saveEmployees();
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    static void addEmployee(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine(); // Changed from nextInt() to nextLine()

        // Check for duplicate ID
        boolean idExists = employees.stream().anyMatch(emp -> emp.id.equals(id));
        if (idExists) {
            System.out.println("Employee with this ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Salary: ");

        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine()); // Handle invalid salary input
            if (salary < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary input! Please enter a valid number.");
            return;
        }

        employees.add(new Employee(id, name, designation, salary));
        System.out.println("Employee added successfully!");
    }

    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    static void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employee data.");
        }
    }

    static void loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            employees = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employee data. Starting fresh.");
            employees = new ArrayList<>();
        }
    }
}

