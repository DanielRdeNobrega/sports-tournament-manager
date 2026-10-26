package teams;

import tournaments.Tournament;

import java.util.ArrayList;
import static views.errors.TeamError.*;
import static views.errors.TournamentError.ACTIVE_TOURNAMENT;

public class TeamList {
    private ArrayList<Team> teamList;

    public TeamList(){
        this.teamList = new ArrayList<>();
    }

    public int getSize(){
        return this.teamList.size();
    }

    public Team getTeam(int index){
        return this.teamList.get(index);
    }

    public void addTeam(Team team){
        if(this.existsTeam(team))
            TEAM_EXISTS.showError();
        else
            this.teamList.add(team);
    }

    public boolean existsTeam(Team team){
        return this.existsTeam(team.getID());
    }

    public boolean existsTeam(String name){
        return this.find(name)!= null;
    }

    public Team find(String name){
        Team team = null;

        if(!this.teamList.isEmpty()){
            for(int i = 0; i<this.teamList.size() && team==null; i++){
                if(this.teamList.get(i).getID().equalsIgnoreCase(name))
                    team = this.teamList.get(i);
            }
        }
        return team;
    }

    public void removeTeam(String name, Tournament tournament){
        Team team = this.find(name);
        this.removeTeam(team, tournament);
    }
    public void removeTeam(Team team, Tournament tournament){
        if(tournament == null){
            this.teamList.remove(team);
        }
        else{
            if(tournament.isOnCurse())
                ACTIVE_TOURNAMENT.showError();
            else
                this.teamList.remove(team);
        }
    }

}
