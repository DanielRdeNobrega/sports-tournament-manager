package matchmaking;

import tournaments.Tournament;
import users.Admin;

public class StrategyM implements Strategy {
    private String identifier = "-m";
    public void execute(Admin admin, Tournament tournament,String[]data){
        admin.matchMaking(this, tournament, data);
    }

    public String getIdentifier() {
        return identifier;
    }
}
