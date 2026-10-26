package users;

import matchmaking.MatchOperation;
import matchmaking.Strategy;
import matchmaking.StrategyA;
import matchmaking.StrategyM;
import teams.Team;
import teams.TeamList;
import tournaments.Tournament;
import tournaments.TournamentList;
import users.player.Player;
import users.player.PlayerList;
import views.CLI;

import static views.errors.Errors.MATCH_NO_EXIST;
import static views.errors.PlayerError.PLAYER_EXIST;
import static views.errors.PlayerError.PLAYER_NO_EXIST;
import static views.errors.TeamError.TEAM_EXISTS;
import static views.errors.TeamError.TEAM_NO_EXISTS;
import static views.errors.TournamentError.TOURNAMENT_EXIST;
import static views.errors.TournamentError.TOURNAMENT_NO_EXIST;
import static views.errors.Errors.NO_CORRECT_PARAMETERS;
import static views.errors.TeamError.MIN_MATES;

import java.util.ArrayList;

public class Admin extends User {

    private Strategy strategy;
    private final int MATCH_MAKING_PARAMETERS = 4;
    private final int MIN_MATES_PER_TEAM = 2;

    public Admin(String email, String password, String DNI){
        super(email, password, DNI);
    }

    @Override
    public void addCoomands(CLI cli) {
        cli.addCommands(this);
    }

    public void addToTeam(String [] splitData, TeamList teams, PlayerList players){
        if(teams.existsTeam(splitData[splitData.length-1])) {
            PlayerList newPlayers = new PlayerList();
            Player player = players.find(splitData[0]);
            if(player!=null) {
                newPlayers.addPlayer(player);
                teams.find(splitData[1]).addMates(newPlayers);
            }
            else
                PLAYER_NO_EXIST.showError();
        }
        else
            TEAM_NO_EXISTS.showError();
    }

    public void createPlayer(String [] splitData, ArrayList<User>users, PlayerList players){
        Player player = new Player(splitData[0],splitData[1],splitData[2],splitData[3],splitData[4],this);
        if(!players.existsPlayer(player)){
            players.addPlayer(player);
            users.add(player);
        }
        else
            PLAYER_EXIST.showError();
    }

    public void createTeam(String [] splitData, PlayerList players, TeamList teams){
        if(teams.existsTeam(splitData[splitData.length-1]))
            TEAM_EXISTS.showError();
        else{
            PlayerList teamMates = new PlayerList();
            for(int i=0; i< splitData.length-1; i++){
                if(players.existsPlayer(splitData[i]))
                    teamMates.addPlayer(players.find(splitData[i]));
            }
            if(teamMates.getSize()>=MIN_MATES_PER_TEAM)
                teams.addTeam(new Team(teamMates,splitData[splitData.length-1],this));
            else
                MIN_MATES.showError();
        }
    }

    public void createTournament(String [] splitData, TournamentList tournaments){
        Tournament tournament = new Tournament(splitData[0],splitData[1], splitData[2], splitData[3],splitData[4],splitData[5]);
        if(!tournaments.existsTournament(tournament))
            tournaments.addTournament(tournament);
        else
            TOURNAMENT_EXIST.showError();
    }

    public void deletePlayer(String data, PlayerList players, ArrayList<User>users, TournamentList tournaments){
        if(players.existsPlayer(data)){
            Tournament tournament = null;
            for(int i=0; i< tournaments.getSize() && tournament == null; i++)
                if(tournaments.getTournament(i).containsPlayer(players.find(data)))
                    tournament = tournaments.getTournament(i);
            Player player = players.find(data);
            players.removePlayer(data, tournament);
            System.out.println(users.size());
            users.remove(player);
            System.out.println(users.size());
        }
        else
            PLAYER_NO_EXIST.showError();
    }


    public void deleteTournament(String data, TournamentList tournaments){
        if(tournaments.existsTournament(data))
            tournaments.removeTournament(data);
        else
            TOURNAMENT_NO_EXIST.showError();
    }

    public void deleteTeam(String data, TeamList teams, TournamentList tournaments){
        if(teams.existsTeam(data)){
            Tournament tournament = null;
            for(int i=0; i< tournaments.getSize() && tournament == null; i++)
                if(tournaments.getTournament(i).containsTeam(data))
                    tournament = tournaments.getTournament(i);
            teams.removeTeam(data, tournament);
        }
    }

    public void removeFromTeam(String [] splitData, TeamList teams, PlayerList players){
        if(players.existsPlayer(splitData[0]) && teams.existsTeam(splitData[1])){
            teams.find(splitData[1]).removeMate(players.find(splitData[0]));
        }
        else if(!players.existsPlayer(splitData[0]))
            PLAYER_NO_EXIST.showError();
        else
            TEAM_NO_EXISTS.showError();
    }

    public void removeFinalizedTournaments(TournamentList tournaments){
        for(int i=0; i< tournaments.getSize(); i++)
            if(!tournaments.getTournament(i).isOnCurse())
                tournaments.removeTournament(tournaments.getTournament(i));
    }

    public void matchMaking(String [] splitData, TournamentList tournaments){
        MatchOperation matchOperation = new MatchOperation();
        this.strategy = matchOperation.getStrategy(splitData[0]);
        Tournament tournament = tournaments.find(splitData[1]);

        if(this.strategy !=null && tournament!=null)
            strategy.execute(this, tournament, splitData);
        else if(this.strategy !=null)
            MATCH_NO_EXIST.showError();
        else
            TOURNAMENT_NO_EXIST.showError();
    }

    public void matchMaking(StrategyM strategyM, Tournament tournament, String[]data){
        if(data.length == MATCH_MAKING_PARAMETERS)
            tournament.manualMatchMake(tournament.contains(data[2]),tournament.contains(data[3]));
        else
            NO_CORRECT_PARAMETERS.showError();
    }

    public void matchMaking(StrategyA strategyA, Tournament tournament){
        tournament.randomMatchMake();
    }
}
