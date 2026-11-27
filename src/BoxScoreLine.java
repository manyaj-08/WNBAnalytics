//One player's per-game averages for the series
public class BoxScoreLine {
    private final int gamesPlayed;
    private final double minutesPerGame;
    private final double pointsPerGame;
    private final double fgMade;
    private final double fgAttempted;
    private final double threesMade;
    private final double threesAttempted;
    private final double ftMade;
    private final double ftAttempted;
    private final double rebounds;
    private final double assists;
    private final double turnovers;

    public BoxScoreLine(int gamesPlayed, double minutesPerGame, double pointsPerGame, double fgMade, double fgAttempted, double threesMade, double threesAttempted, double ftMade, double ftAttempted, double rebounds, double assists, double turnovers) {
        this.gamesPlayed = gamesPlayed;
        this.minutesPerGame = minutesPerGame;
        this.pointsPerGame = pointsPerGame;
        this.fgMade = fgMade;
        this.fgAttempted = fgAttempted;
        this.threesMade = threesMade;
        this.threesAttempted = threesAttempted;
        this.ftMade = ftMade;
        this.ftAttempted = ftAttempted;
        this.rebounds = rebounds;
        this.assists = assists;
        this.turnovers = turnovers;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public double getFgAttempted() {
        return fgAttempted;
    }

    public double getThreesAttempted() {
        return threesAttempted;
    }

    public double getFtAttempted() {
        return ftAttempted;
    }

    public double getAssists() {
        return assists;
    }

    public double getTurnovers() {
        return turnovers;
    }

    public double getMinutesPerGame() {
        return minutesPerGame;
    }

    public double getFgMade() {
        return fgMade;
    }

    public double getThreesMade() {
        return threesMade;
    }

    public double getFtMade() {
        return ftMade;
    }

    public double getRebounds() {
        return rebounds;
    }
}