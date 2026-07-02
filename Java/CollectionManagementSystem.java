import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CollectionManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();

        while (true) {
            System.out.println("\n--- Collection Management System ---");
            System.out.println("1. Manage List");
            System.out.println("2. Manage Set");
            System.out.println("3. Manage Map");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (choice == 1) {
                manageList(scanner, list);
            } else if (choice == 2) {
                manageSet(scanner, set);
            } else if (choice == 3) {
                manageMap(scanner, map);
            } else if (choice == 4) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice. Please select from 1 to 4.");
            }
        }
        scanner.close();
    }

    private static void manageList(Scanner scanner, List<String> list) {
        System.out.println("\n-- List Operations --");
        System.out.println("1. Add an element");
        System.out.println("2. Remove an element");
        System.out.println("3. Display all elements");
        System.out.print("Select an operation: ");

        String choice = scanner.nextLine();

        try {
            if (choice.equals("1")) {
                System.out.print("Enter element to add: ");
                list.add(scanner.nextLine());
                System.out.println("Element added successfully.");
            } else if (choice.equals("2")) {
                System.out.print("Enter element to remove: ");
                String elem = scanner.nextLine();
                if (!list.remove(elem)) {
                    throw new Exception("Element not found in the list.");
                }
                System.out.println("Element removed successfully.");
            } else if (choice.equals("3")) {
                System.out.println("Current List: " + list);
            } else {
                System.out.println("Invalid operation.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageSet(Scanner scanner, Set<String> set) {
        System.out.println("\n-- Set Operations --");
        System.out.println("1. Add an element");
        System.out.println("2. Remove an element");
        System.out.println("3. Display all elements");
        System.out.print("Select an operation: ");

        String choice = scanner.nextLine();

        try {
            if (choice.equals("1")) {
                System.out.print("Enter element to add: ");
                set.add(scanner.nextLine());
                System.out.println("Element added successfully.");
            } else if (choice.equals("2")) {
                System.out.print("Enter element to remove: ");
                String elem = scanner.nextLine();
                if (!set.remove(elem)) {
                    throw new Exception("Element not found in the set.");
                }
                System.out.println("Element removed successfully.");
            } else if (choice.equals("3")) {
                System.out.println("Current Set: " + set);
            } else {
                System.out.println("Invalid operation.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageMap(Scanner scanner, Map<String, String> map) {
        System.out.println("\n-- Map Operations --");
        System.out.println("1. Add an element");
        System.out.println("2. Remove an element");
        System.out.println("3. Display all elements");
        System.out.print("Select an operation: ");

        String choice = scanner.nextLine();

        try {
            if (choice.equals("1")) {
                System.out.print("Enter key: ");
                String key = scanner.nextLine();
                if (map.containsKey(key)) {
                    throw new Exception("Duplicate key in the map.");
                }
                System.out.print("Enter value: ");
                String value = scanner.nextLine();
                map.put(key, value);
                System.out.println("Key-Value pair added successfully.");
            } else if (choice.equals("2")) {
                System.out.print("Enter key to remove: ");
                String key = scanner.nextLine();
                if (map.remove(key) == null) {
                    throw new Exception("Key not found in the map.");
                }
                System.out.println("Element removed successfully.");
            } else if (choice.equals("3")) {
                System.out.println("Current Map: " + map);
            } else {
                System.out.println("Invalid operation.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}