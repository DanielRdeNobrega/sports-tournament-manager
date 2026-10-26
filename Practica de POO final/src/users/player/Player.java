package users.player;

import tournaments.Participant;
import users.Admin;
import users.User;
import views.CLI;

import static views.errors.PlayerError.*;

public class Player extends User implements Participant {
    private final String name;
    private final String surname;
    private Statistic [] statistics;
    private final int MAX_STATISTICS = 5;
    private final Admin admin;

    public Player(String name, String surname, String DNI,
                  String email, String password, Admin admin){
        super(email, password, DNI);
        this.name = name;
        this.surname = surname;
        this.admin = admin;
        this.initializeStatistics();
    }

    private void initializeStatistics(){
        this.statistics = new Statistic[MAX_STATISTICS];
        statistics[0] = new Statistic(0.0, "Puntos marcados");
        statistics[1] = new Statistic(0.0, "Partidos ganados");
        statistics[2] = new Statistic(0.0, "Puntos de asistencia");
        statistics[3] = new Statistic(0.0, "Torneos ganados");
        statistics[4] = new Statistic(0.0, "Dinero generado en el torneo");
    }

    private int findStatistic(String statistic){
        for(int i=0; i<MAX_STATISTICS; i++)
            if(statistic.equalsIgnoreCase(this.statistics[i].getName()))
                return i;
        return -1;
    }

    public void setStatistics(String statistic, double value){
        int index = this.findStatistic(statistic);
        if(index != -1)
            this.statistics[index].setStatistic(value);
    }

    public void setStatistics(double [] newValues){
        if(newValues.length>MAX_STATISTICS || newValues.length<MAX_STATISTICS)
            OVER_MAX_STATISTIC.showError();
        else
            for(int i = 0; i<MAX_STATISTICS; i++)
                this.statistics[i].setStatistic(newValues[i]);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getID() {
        return super.getID();
    }

    public double getStatistics(String statistic) {
        int index = this.findStatistic(statistic);
        return this.getStatistics(index);
    }

    public double getStatistics(int index){
        if(index==-1){
            STATISTIC_NO_EXIST.showError();
            return -1;
        }
        else
            return this.statistics[index].getStatistic();
    }

    public Statistic[] getStatistics() {
        return statistics;
    }

    public boolean containsPlayer(Player player){
        return player.getID().equalsIgnoreCase(this.getID());
    }

    @Override
    public void addCoomands(CLI cli) {
        cli.addCommands(this);
    }
}
