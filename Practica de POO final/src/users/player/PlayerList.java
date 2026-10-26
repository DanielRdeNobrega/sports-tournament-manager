package users.player;

import tournaments.Tournament;

import java.util.ArrayList;

import static views.errors.TournamentError.ACTIVE_TOURNAMENT;

public class PlayerList {
    private ArrayList<Player> playerList;

    public PlayerList(){
        playerList = new ArrayList<>();
    }

    public void addPlayer(Player player){
        this.playerList.add(player);
    }

    public boolean existsPlayer(Player player){
        return this.existsPlayer(player.getID());
    }

    public boolean existsPlayer(String DNI){
        return this.find(DNI)!= null;
    }

    public Player find(String DNI){
        Player player = null;

        if(!this.playerList.isEmpty()){
            for(int i = 0; i<this.playerList.size() && player==null; i++){
                if(this.playerList.get(i).getID().equalsIgnoreCase(DNI))
                    player = this.playerList.get(i);
            }
        }
        return player;
    }

    public void removePlayer(String DNI, Tournament tournament){
        Player player = this.find(DNI);
        this.removePlayer(player,tournament);
    }

    private void removePlayer(Player player, Tournament tournament){
        if(tournament==null){
            this.playerList.remove(player);
        }
        else{
            if(tournament.isOnCurse())
                ACTIVE_TOURNAMENT.showError();
            else
                this.playerList.remove(player);
        }
    }

    public int getSize(){ return this.playerList.size();}

    public Player getPlayer(int i){return this.playerList.get(i);}

}