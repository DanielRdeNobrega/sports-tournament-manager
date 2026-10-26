package tournaments;

import java.util.ArrayList;
import static views.errors.TournamentError.*;

public class TournamentList {
    private ArrayList<Tournament> tournamentList;

    public TournamentList(){
        this.tournamentList = new ArrayList<>();
    }

    public void addTournament(Tournament tournament){
        if(this.existsTournament(tournament.getName()))
            TOURNAMENT_EXIST.showError();
        else
            this.tournamentList.add(tournament);
    }

    public Tournament find(String tournamentName){
        Tournament tournament = null;

        if(!this.tournamentList.isEmpty()){
            for(int i = 0; i<this.tournamentList.size() && tournament==null; i++){
                if(this.tournamentList.get(i).getName().equalsIgnoreCase(tournamentName))
                    tournament = this.tournamentList.get(i);
            }
        }
        return tournament;
    }

    public boolean existsTournament(Tournament tournament){
        return this.existsTournament(tournament.getName());
    }

    public boolean existsTournament(String tournamentName){
        return this.find(tournamentName)!=null;
    }

    public void removeTournament(String tournamentName){
        if(!tournamentList.isEmpty() && existsTournament(tournamentName)){
            this.tournamentList.remove(this.find(tournamentName));
        }
        else if(tournamentList.isEmpty())
            NO_TOURNAMENTS.showError();
        else
            TOURNAMENT_NO_EXIST.showError();
    }

    public void removeTournament(Tournament tournament){
        this.removeTournament(tournament.getName());
    }

    public int getSize(){
        return this.tournamentList.size();
    }

    public Tournament getTournament(int index){
        return this.tournamentList.get(index);
    }

}