public class StatCalculator {

    // True shooting %: points per shooting possession. The 0.44 estimates how many free throw
    // attempts end a possession. Per-game inputs work because games played cancels out of the ratio.
    public static double trueShootingPct(Player p) {
        BoxScoreLine s = p.getStats();
        double shootingPossessions = 2 * (s.getFgAttempted() + 0.44 * s.getFtAttempted());
        if (shootingPossessions == 0) return 0.0; // player never shot; avoid dividing by zero
        return s.getPointsPerGame() / shootingPossessions * 100;
    }

    // Effective FG %: gives a made three 1.5x the credit of a made two
    public static double effectiveFgPct(Player p) {
        BoxScoreLine s = p.getStats();
        if (s.getFgAttempted() == 0) return 0.0;
        return (s.getFgMade() + 0.5 * s.getThreesMade()) / s.getFgAttempted() * 100;
    }
}