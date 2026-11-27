public class StatCalculatorCheck {
    public static void main(String[] args) {
        // 20 pts, 7/12 FG, 3/5 3PT, 3/4 FT -> TS% = 20 / (2 * (12 + 0.44 * 4)) = 72.7
        Player shooter = new Player("Test Shooter", "ATL",
                new BoxScoreLine(1, 30.0, 20.0, 7.0, 12.0, 3.0, 5.0, 3.0, 4.0, 6.0, 4.0, 2.0));
        check("TS% normal case", StatCalculator.trueShootingPct(shooter), 72.7);
        check("eFG% normal case", StatCalculator.effectiveFgPct(shooter), 70.8);

        // A player with no shots, like Shatori Walker-Kimbrough in the real data
        Player benchPlayer = new Player("Test Bench", "GSV",
                new BoxScoreLine(1, 2.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        check("TS% zero attempts", StatCalculator.trueShootingPct(benchPlayer), 0.0);
        check("eFG% zero attempts", StatCalculator.effectiveFgPct(benchPlayer), 0.0);
    }

    private static void check(String label, double actual, double expected) {
        boolean pass = Math.abs(actual - expected) < 0.05;
        System.out.println((pass ? "PASS: " : "FAIL: ") + label + " (got " + actual + ")");
    }
}