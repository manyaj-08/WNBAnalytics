import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "data/box_scores.csv";

    public static void main(String[] args) {
        List<Player> players;
        try {
            players = CsvLoader.load(DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            if (!scanner.hasNextLine()) {
                break; // input stream closed; exit instead of crashing
            }
            // Read a String, not nextInt(), so a typed letter can't throw an exception
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("Lookup coming soon");
            } else if (choice.equals("2")) {
                System.out.println("Compare coming soon");
            } else if (choice.equals("3")) {
                running = false;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== WNBAnalytics ===");
        System.out.println("1. Look up a player");
        System.out.println("2. Compare two players");
        System.out.println("3. Quit");
        System.out.print("Choose an option: ");
    }
}