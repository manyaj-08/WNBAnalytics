import java.text.Normalizer;

public class Player {
    private final String name;
    private final String team;
    private final BoxScoreLine stats;

    public Player(String name, String team, BoxScoreLine stats) {
        this.name = name;
        this.team = team;
        this.stats = stats;
    }

    // One way to make sure names are looked up so "salaun" still means "Salaün" and extra spaces don't matter
    public static String makeKey(String name) {
        String noAccents = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return noAccents.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public BoxScoreLine getStats() {
        return stats;
    }
}