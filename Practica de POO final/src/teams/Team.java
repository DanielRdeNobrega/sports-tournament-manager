package teams;

import tournaments.Participant;
import users.Admin;
import users.player.Player;
import users.player.PlayerList;
import users.player.Statistic;

import java.util.ArrayList;

import static views.errors.TeamError.*;

public class Team implements Participant {
    private String teamName;
    private ArrayList<Player> teamMates;
    private Statistic [] statistics;
    private static final int MAX_STATISTICS = 5;
    private static final int MINIMUM_MATES = 2;
    private final Admin admin;

    public Team(PlayerList players, String name, Admin admin){
        this.teamMates= new ArrayList<>();
        this.teamName = name;
        this.admin = admin;
        this.initializeStatistics();
        this.calculateStatistics();
        this.addMates(players);
    }

    private void initializeStatistics(){
        this.statistics = new Statistic[MAX_STATISTICS];
        statistics[0] = new Statistic(0.0, "Puntos marcados");
        statistics[1] = new Statistic(0.0, "Partidos ganados");
        statistics[2] = new Statistic(0.0, "Puntos de asistencia");
        statistics[3] = new Statistic(0.0, "Torneos ganados");
        statistics[4] = new Statistic(0.0, "Dinero generado en el torneo");
    }

    private void calculateStatistics(){

        for(int i = 0; i < this.MAX_STATISTICS; i++){
            double solution =1;
            int count = 0;
            for(int j = 0; j < this.teamMates.size(); j++){
                double stat = this.teamMates.get(j).getStatistics(i);
                if(stat > 0) {
                    solution *= stat;
                    count++;
                }
            }
            if(count > 0)
                this.statistics[i].setStatistic(Math.pow(solution,(1.0/ count)));
            else
                this.statistics[i].setStatistic(0);
        }
    }

    public void addMates(PlayerList players){
        for(int i=0; i< players.getSize(); i++){
            Player player = players.getPlayer(i);
            if(this.teamMates.contains(player)){
                ALREADY_IN.showError();
            }
            else{
                this.teamMates.add(player);
                this.calculateStatistics();
            }
        }
    }

    public void removeMate(Player player){
        if(this.teamMates.size()>2) {
            if (this.teamMates.contains(player)) {
                this.teamMates.remove(player);
                this.calculateStatistics();
            }
            else
                ALREADY_OUT.showError();
        }
        else
            MIN_MATES.showError();
    }

    public String getID() {
        return this.teamName;
    }

    public double getStatistics(String statistic){
        for(int i=0; i<MAX_STATISTICS; i++)
            if(statistic.equalsIgnoreCase(this.statistics[i].getName()))
                return statistics[i].getStatistic();

        STATISTIC_NO_EXIST.showError();
        return -1;
    }

    public boolean containsPlayer(Player player){
        return this.teamMates.contains(player);
    }

}