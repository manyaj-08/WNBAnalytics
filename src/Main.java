import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {


    //Loops through the list and returns the matching player or null if nobody matches
    private static Player findPlayer(List<Player> players, String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                return p;
            }
        }
        return null;
    }
    //The not found logic is in one place
    private static Player promptForPlayer(Scanner scanner, List<Player> players, String prompt) {
        while (true) {
            System.out.print(prompt + " (or 'back' to return to the menu): ");
            if (!scanner.hasNextLine()) {
                return null;
            }
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("back")) {
                return null;
            }

            Player player = findPlayer(players, input);
            if (player != null) {
                return player;
            }
            System.out.println("Player not found. Check the spelling and try again.");
        }
    }

    private static void lookUpPlayer(Scanner scanner, List<Player> players) {
        Player player = promptForPlayer(scanner, players, "Enter a player's name");
        if (player == null) {
            return;
        }
        printPlayerStats(player);
    }

    private static void printPlayerStats(Player p) {
        BoxScoreLine s = p.getStats();
        System.out.println();
        System.out.println(p.getName() + " (" + p.getTeam() + ")");
        System.out.printf("Games played:    %d%n", s.getGamesPlayed());
        System.out.printf("Points per game: %.1f%n", s.getPointsPerGame());
        System.out.printf("True shooting:   %.1f%%%n", StatCalculator.trueShootingPct(p));
        System.out.printf("Effective FG:    %.1f%%%n", StatCalculator.effectiveFgPct(p));
    }






    private static void comparePlayers(Scanner scanner, List<Player> players) {
        Player first = promptForPlayer(scanner, players, "Enter the first player's name");
        if (first == null) {
            return;
        }
        Player second = promptForPlayer(scanner, players, "Enter the second player's name");
        if (second == null) {
            return;
        }

        System.out.println();
        System.out.printf("%-16s %-26s %-26s %s%n", "Stat", first.getName(), second.getName(), "Leader");
        printComparisonRow("Points/game", first.getStats().getPointsPerGame(),
                second.getStats().getPointsPerGame(), first, second);
        printComparisonRow("True shooting %", StatCalculator.trueShootingPct(first),
                StatCalculator.trueShootingPct(second), first, second);
        printComparisonRow("Effective FG %", StatCalculator.effectiveFgPct(first),
                StatCalculator.effectiveFgPct(second), first, second);
    }

    private static void printComparisonRow(String label, double firstValue, double secondValue,
                                           Player first, Player second) {
        //Compare the values rounded to one decimal so two numbers that are the same is a tie
        long firstRounded = Math.round(firstValue * 10);
        long secondRounded = Math.round(secondValue * 10);

        String leader;
        if (firstRounded > secondRounded) {
            leader = first.getName();
        } else if (secondRounded > firstRounded) {
            leader = second.getName();
        } else {
            leader = "Tie";
        }
        System.out.printf("%-16s %-26.1f %-26.1f %s%n", label, firstValue, secondValue, leader);
    }








    private static void rankPlayers(Scanner scanner, List<Player> players) {
        System.out.println("Rank by: 1) Points per game  2) True shooting %  3) Effective FG %");
        System.out.print("Enter 1, 2, or 3: ");
        if (!scanner.hasNextLine()) {
            return;
        }
        String input = scanner.nextLine().trim();

        int statChoice;
        if (input.equals("1")) {
            statChoice = 1;
        } else if (input.equals("2")) {
            statChoice = 2;
        } else if (input.equals("3")) {
            statChoice = 3;
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        List<Player> ranked = PlayerRanker.rank(players, statChoice);
        for (int i = 0; i < ranked.size(); i++) {
            Player p = ranked.get(i);
            System.out.printf("%2d. %-26s %s  %.1f%n", i + 1, p.getName(), p.getTeam(),
                    PlayerRanker.statValue(p, statChoice));
        }
    }
















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
                break; //Input stream closed so it exits instead of crashing
            }
            //Read a string not int so text can't throw an exception
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                lookUpPlayer(scanner, players);
            } else if (choice.equals("2")) {
                comparePlayers(scanner, players);
            } else if (choice.equals("3")) {
                running = false;
            } else if (choice.equals("4")) {
                rankPlayers(scanner, players);
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
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
        System.out.println("4. Rank players");
        System.out.print("Choose an option: ");
    }
}