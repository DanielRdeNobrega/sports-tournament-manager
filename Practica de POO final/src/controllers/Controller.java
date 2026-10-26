package controllers;

import teams.Team;
import teams.TeamList;
import tournaments.Tournament;
import tournaments.TournamentList;
import users.Admin;
import users.User;
import users.player.Player;
import users.player.PlayerList;
import users.player.Statistic;
import static views.errors.TournamentError.PLAYER_NO_EXISTS_IN_TOURNAMENT;
import static views.errors.TournamentError.PLAYER_EXISTS_IN_TOURNAMENT;
import static views.errors.TournamentError.ACTIVE_TOURNAMENT;
import static views.errors.Errors.NO_CORRECT_PARAMETERS;
import java.util.ArrayList;

public class Controller {
    private ArrayList<User>users;
    private TeamList teams;
    private PlayerList players;
    private TournamentList tournaments;
    private final int PLAYER_PARAMETERS = 5;
    private final int PARAMETERS = 2;
    private final int REMOVE_PARAMETERS = 1;
    private final int TOURNAMENT_PARAMETERS = 6;

    public Controller(){
        this.users = new ArrayList<>();
        this.players = new PlayerList();
        this.teams = new TeamList();
        this.tournaments = new TournamentList();
        users.add(new Admin("rosadeguadalupe@gmail.com","rosadeguadalupe","01111111A"));
    }

    private String[] split(String data){
        String [] splitData = data.split(" ")[1].split(";");
        return splitData;
    }

    public void addToTeam(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == PARAMETERS)
            admin.addToTeam(splitData, this.teams, this.players);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void createPlayer(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == PLAYER_PARAMETERS)
            admin.createPlayer(splitData, this.users, this.players);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void createTeam(String data, Admin admin){
        String [] splitData = this.split(data);
        admin.createTeam(splitData, players, teams);
    }

    public void createTournament(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == TOURNAMENT_PARAMETERS)
            admin.createTournament(splitData, this.tournaments);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void deletePlayer(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == REMOVE_PARAMETERS)
            admin.deletePlayer(splitData[0], this.players, this.users, this.tournaments);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void deleteTeam(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == REMOVE_PARAMETERS)
            admin.deleteTeam(splitData[0], this.teams, this.tournaments);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void deleteTournament(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == REMOVE_PARAMETERS)
            admin.deleteTournament(splitData[0], this.tournaments);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void removeFromTeam(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length == PARAMETERS)
            admin.removeFromTeam(splitData, this.teams, this.players);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    private void removeFinalizedTournaments(Admin admin){
        admin.removeFinalizedTournaments(this.tournaments);
    }

    private void ranked(){
        for(int i=0; i<this.tournaments.getSize(); i++) {
            if (!tournaments.getTournament(i).getParticipants().isEmpty())
                tournaments.getTournament(i).rank();
        }
    }
    public TournamentList getTournamentList(Admin admin){
        this.removeFinalizedTournaments(admin);
        this.ranked();
        return this.tournaments;
    }

    public TournamentList getTournamentList(Player player) {
        this.ranked();
        return this.tournaments;
    }

    public TournamentList getTournamentList(){
        for(int i=0; i<this.tournaments.getSize(); i++)
            tournaments.getTournament(i).shuffleParticipants();
        return this.tournaments;
    }

    public void matchMaking(String data, Admin admin){
        String [] splitData = this.split(data);
        if(splitData.length >= PARAMETERS)
            admin.matchMaking(splitData, this.tournaments);
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void addToTournament(String data, Player player){
        String [] splitData = this.split(data);
        if(splitData.length < REMOVE_PARAMETERS){
            NO_CORRECT_PARAMETERS.showError();
        }
        else {
            Tournament tournament = this.tournaments.find(splitData[0]);
            if (tournament != null) {
                if (tournament.containsPlayer(player))
                    PLAYER_EXISTS_IN_TOURNAMENT.showError();
                else {
                    if(!tournament.isOnCurse()){
                        if (splitData.length == PARAMETERS){
                            Team team = this.teams.find(splitData[1]);
                            if (team != null && team.containsPlayer(player))
                                tournament.register(team);
                            else
                                tournament.register(player);
                        }
                        else
                            tournament.register(player);
                    }
                    else
                        ACTIVE_TOURNAMENT.showError();
                }
            }
        }
    }

    public void removeFromTournament(String data, Player player){
        String [] splitData = this.split(data);
        if(splitData.length < REMOVE_PARAMETERS){
            NO_CORRECT_PARAMETERS.showError();
        }
        else{
            Tournament tournament = this.tournaments.find(splitData[0]);
            if(!tournament.containsPlayer(player)){
                PLAYER_NO_EXISTS_IN_TOURNAMENT.showError();
            }
            else{
                if (splitData.length == PARAMETERS){
                    Team team = this.teams.find(splitData[1]);
                    if (team != null && team.containsPlayer(player))
                        tournament.removeParticipant(team);
                    else
                        tournament.removeParticipant(player);
                }
                else
                    tournament.removeParticipant(player);
            }
        }
    }

    public Statistic[] getStatistics(Player player){
        return player.getStatistics();
    }

    public User searchUser(String data){
        String [] splitData = this.split(data);
        User userChossen = null;
        if(splitData.length == PARAMETERS){
            for(int i=0; i<this.users.size() && userChossen==null; i++){
                User user = this.users.get(i);
                if(user.getEmail().equalsIgnoreCase(splitData[0]) &&
                        user.getPassword().equalsIgnoreCase(splitData[1]))
                    userChossen = user;
            }
        }
        else
            NO_CORRECT_PARAMETERS.showError();
        return userChossen;
    }
}
