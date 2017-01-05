package viking.api.stats;

import viking.api.stats.enums.Stat;
import viking.api.stats.enums.StatType;
import viking.framework.VMethodProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class Stats extends VMethodProvider {

    public static int getPlayerStat(String player, Stat stat, StatType skill_type) {
        final String text = getPlayerLevelStats(player, stat);
        if (text == null)
            return -1;

        return Integer.parseInt(text.split(",")[skill_type.getSkillPosition()]);
    }

    /**
     * Gets the specified level of the specified RSPlayer from the Runescape website.
     *
     * @param player The player to get the stat of.
     * @param stat   The skill to get the stat of.
     * @return The stats of the specified skills from the specified RSPlayer.
     */
    private static String getPlayerLevelStats(String player, Stat stat) {
        try {
            URL url = new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + player);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {

                final String[] stats = new String[Stat.values().length + 1];
                for (int i = 0; i < Stat.values().length + 1; i++) {

                    final String line = reader.readLine();
                    if (line != null) {
                        stats[i] = line;
                    }
                }

                reader.close();
                return stats[stat.getSkillPosition()];
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

