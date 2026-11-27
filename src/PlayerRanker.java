import java.util.ArrayList;
import java.util.List;

public class PlayerRanker {
    public static final int MIN_FG_ATTEMPTS = 20;

    //Stat choice: 1 = points per game, 2 = true shooting %, 3 = effective FG %
    public static double statValue(Player p, int statChoice) {
        if (statChoice == 1) {
            return p.getStats().getPointsPerGame();
        } else if (statChoice == 2) {
            return StatCalculator.trueShootingPct(p);
        } else {
            return StatCalculator.effectiveFgPct(p);
        }
    }

    public static List<Player> rank(List<Player> players, int statChoice) {
        List<Player> ranked = new ArrayList<>();
        for (Player p : players) {
            //Attempts per game * games played ~ total attempts.
            double totalAttempts = p.getStats().getFgAttempted() * p.getStats().getGamesPlayed();
            if (totalAttempts >= MIN_FG_ATTEMPTS) {
                ranked.add(p);
            }
        }

        //Highest value first: find the best player from position i onward and then swap to position i
        for (int i = 0; i < ranked.size() - 1; i++) {
            int bestIndex = i;
            for (int j = i + 1; j < ranked.size(); j++) {
                if (statValue(ranked.get(j), statChoice) > statValue(ranked.get(bestIndex), statChoice)) {
                    bestIndex = j;
                }
            }
            Player temp = ranked.get(i);
            ranked.set(i, ranked.get(bestIndex));
            ranked.set(bestIndex, temp);
        }
        return ranked;
    }
}