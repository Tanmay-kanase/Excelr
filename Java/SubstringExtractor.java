import java.util.Scanner;

public class SubstringExtractor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        try {
            System.out.print("Enter starting index: ");
            int startIndex = scanner.nextInt();

            System.out.print("Enter ending index: ");
            int endIndex = scanner.nextInt();

            if (startIndex < 0 || endIndex > inputString.length() || startIndex > endIndex) {
                System.out.println("Error: Invalid indices provided.");
            } else {
                String result = inputString.substring(startIndex, endIndex);
                System.out.println("Extracted Substring: " + result);
            }
        } catch (Exception e) {
            System.out.println("Error: Please enter valid integer indices.");
        } finally {
            scanner.close();
        }
    }
}