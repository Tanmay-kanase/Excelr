import java.util.Scanner;

public class SentenceSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        if (sentence.trim().isEmpty()) {
            System.out.println("The input sentence is empty.");
        } else {
            String[] words = sentence.split("\\s+");

            System.out.println("\nWords in the sentence:");
            for (int i = 0; i < words.length; i++) {
                System.out.println("Word " + (i + 1) + ": " + words[i]);
            }
        }

        scanner.close();
    }
}