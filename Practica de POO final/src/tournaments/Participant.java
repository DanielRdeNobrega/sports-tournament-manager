package tournaments;

import users.player.Player;

public interface Participant {
    String getID();
    double getStatistics(String statistic);
    boolean containsPlayer(Player player);
}
