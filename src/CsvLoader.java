import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    private static final int EXPECTED_COLUMNS = 14;

    public static List<Player> load(String filePath) throws IOException {
        List<Player> players = new ArrayList<>();

        //try-catch loop if an exception is thrown
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("The data file is empty.");
            }

            int lineNumber = 1;
            String line = reader.readLine();
            while (line != null) {
                lineNumber++;
                if (!line.isBlank()) {
                    Player player = parseLine(line, lineNumber);
                    if (containsName(players, player.getName())) {
                        //Check if two rows is the same player
                        throw new IOException("Line " + lineNumber + ": duplicate player " + player.getName());
                    }
                    players.add(player);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Data file not found: " + filePath);
        }

        if (players.isEmpty()) {
            throw new IOException("The data file has no player rows.");
        }
        return players;
    }

    private static boolean containsName(List<Player> players, String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private static Player parseLine(String line, int lineNumber) throws IOException {
        String[] fields = line.split(",");
        if (fields.length != EXPECTED_COLUMNS) {
            throw new IOException("Line " + lineNumber + ": expected " + EXPECTED_COLUMNS
                    + " columns but found " + fields.length);
        }

        try {
            BoxScoreLine stats = new BoxScoreLine(
                    Integer.parseInt(fields[2].trim()),
                    toStat(fields[3]), toStat(fields[4]), toStat(fields[5]), toStat(fields[6]),
                    toStat(fields[7]), toStat(fields[8]), toStat(fields[9]), toStat(fields[10]),
                    toStat(fields[11]), toStat(fields[12]), toStat(fields[13]));
            return new Player(fields[0].trim(), fields[1].trim(), stats);
        } catch (NumberFormatException e) {
            throw new IOException("Line " + lineNumber + ": a stat column is not a valid non-negative number");
        }
    }

    //Check the value for negative and NaN so it doesn't mess up the math later
    private static double toStat(String text) {
        double value = Double.parseDouble(text.trim());
        if (Double.isNaN(value) || value < 0) {
            throw new NumberFormatException(text);
        }
        return value;
    }
}