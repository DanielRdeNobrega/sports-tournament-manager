package matchmaking;

import tournaments.Tournament;
import users.Admin;

public interface Strategy {
    void execute(Admin admin, Tournament tournament, String [] data);

    public String getIdentifier();

}
