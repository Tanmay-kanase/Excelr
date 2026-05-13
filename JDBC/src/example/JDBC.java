package example;

import java.sql.*;
import java.util.*;
public class JDBC {
    static final String DB_URL = "jdbc:mysql://localhost:3306/emp_mngt";
    static final String USER = "root"; 
    static final String PASS = "pokemon";

    public static void main(String[] args) {
      
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            boolean running = true;
            
            while (running) {
                System.out.println("\n=== Employee Management System ===");
                System.out.println("1. Add a new employee");
                System.out.println("2. Update employee details");
                System.out.println("3. Delete an employee");
                System.out.println("4. Display all employees");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addEmployee(conn, scanner);
                        break;
                    case 2:
                        updateEmployee(conn, scanner);
                        break;
                    case 3:
                        deleteEmployee(conn, scanner);
                        break;
                    case 4:
                        displayEmployees(conn);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void addEmployee(Connection conn, Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, department);
            pstmt.setDouble(4, salary);
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Success: Employee added to the database!");
            }
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }


    private static void updateEmployee(Connection conn, Scanner scanner) {
        System.out.print("Enter the ID of the employee you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter new Department: ");
        String department = scanner.nextLine();
        
        System.out.print("Enter new Salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Success: Employee details updated!");
            } else {
                System.out.println("Error: Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }


    private static void deleteEmployee(Connection conn, Scanner scanner) {
        System.out.print("Enter the ID of the employee you want to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Success: Employee deleted successfully!");
            } else {
                System.out.println("Error: Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }


    private static void displayEmployees(Connection conn) {
        String sql = "SELECT * FROM employees";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- Employee List ---");
            System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Name", "Department", "Salary");
            System.out.println("---------------------------------------------------------------");
            
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                
                System.out.printf("%-10d %-20s %-20s %-10.2f\n", id, name, department, salary);
            }
            
            if (!hasRecords) {
                System.out.println("No employees found in the database.");
            }
            System.out.println("---------------------------------------------------------------");
            
        } catch (SQLException e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
        }
    }
}
