public class Main {
    public static void main(String[] args) {
        System.out.println("WNBAnalytics starting...");
    }
}


/*
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Player> players = CsvLoader.load("data/box_scores.csv");
            for (Player p : players) {
                System.out.println(p.getName() + " - " + p.getStats().getPointsPerGame() + " ppg");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
*/
