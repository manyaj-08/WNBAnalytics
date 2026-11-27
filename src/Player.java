public class Player {
    private final String name;
    private final String team;
    private final BoxScoreLine stats;

    public Player(String name, String team, BoxScoreLine stats) {
        this.name = name;
        this.team = team;
        this.stats = stats;
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