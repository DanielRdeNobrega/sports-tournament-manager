package matchmaking;

import tournaments.Tournament;
import users.Admin;

public class StrategyA implements Strategy {
    private String identifier = "-a";
    public void execute(Admin admin, Tournament tournament, String[]data){
        admin.matchMaking(this, tournament);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
