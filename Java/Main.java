import java.util.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDepartment(String department) {
        this.department = department;
    }

    public void updateSalary(double salary) {
        this.salary = salary;
    }
}

class EmployeeManagementSystem {
    private HashMap<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Employee e) {
        if (employees.containsKey(e.getId())) {
            throw new RuntimeException("Employee ID already exists");
        }
        employees.put(e.getId(), e);
    }

    public void updateEmployee(int id, String name, String dept, Double salary) {
        if (!employees.containsKey(id)) {
            throw new RuntimeException("Employee not found");
        }
        Employee e = employees.get(id);
        if (name != null)
            e.updateName(name);
        if (dept != null)
            e.updateDepartment(dept);
        if (salary != null)
            e.updateSalary(salary);
    }

    public void deleteEmployee(int id) {
        if (!employees.containsKey(id)) {
            throw new RuntimeException("Employee not found");
        }
        employees.remove(id);
    }

    public Employee searchEmployee(int id) {
        if (!employees.containsKey(id)) {
            throw new RuntimeException("Employee not found");
        }
        return employees.get(id);
    }

    public void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No employees found");
            return;
        }
        for (Employee e : employees.values()) {
            System.out.println(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeManagementSystem ems = new EmployeeManagementSystem();

        while (true) {
            System.out.println("\n1.Add 2.Update 3.Delete 4.Display 5.Search 6.Exit");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input");
                sc.next();
                continue;
            }

            try {
                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    ems.addEmployee(new Employee(id, name, dept, salary));
                    System.out.println("Employee added");
                } else if (choice == 2) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name or press enter: ");
                    String name = sc.nextLine();
                    if (name.isEmpty())
                        name = null;
                    System.out.print("Enter new department or press enter: ");
                    String dept = sc.nextLine();
                    if (dept.isEmpty())
                        dept = null;
                    System.out.print("Enter new salary or -1: ");
                    double sal = sc.nextDouble();
                    Double salary = sal == -1 ? null : sal;
                    ems.updateEmployee(id, name, dept, salary);
                    System.out.println("Employee updated");
                } else if (choice == 3) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    ems.deleteEmployee(id);
                    System.out.println("Employee deleted");
                } else if (choice == 4) {
                    ems.displayAll();
                } else if (choice == 5) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.println(ems.searchEmployee(id));
                } else if (choice == 6) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}