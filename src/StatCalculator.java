public class StatCalculator {

    //True shooting percentage: points per shooting possession and the 0.44 estimates how many free throw
    public static double trueShootingPct(Player p) {
        BoxScoreLine s = p.getStats();
        double shootingPossessions = 2 * (s.getFgAttempted() + 0.44 * s.getFtAttempted());
        if (shootingPossessions == 0) return 0.0; // player never shot; avoid dividing by zero
        return s.getPointsPerGame() / shootingPossessions * 100;
    }

    //Effective field goal percentage: gives a made three pointer 1.5 times the credit of a made two pointer
    public static double effectiveFgPct(Player p) {
        BoxScoreLine s = p.getStats();
        if (s.getFgAttempted() == 0) return 0.0;
        return (s.getFgMade() + 0.5 * s.getThreesMade()) / s.getFgAttempted() * 100;
    }
}