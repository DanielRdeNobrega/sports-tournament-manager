package views.commands.playerCommands;

import controllers.Controller;
import tournaments.Participant;
import tournaments.Tournament;
import tournaments.TournamentList;
import users.player.Player;
import views.commands.Command;

import java.util.ArrayList;

public class TournamentListPlayerCommand extends Command {
    private Player player;
    public TournamentListPlayerCommand(Controller controller, Player player){
        super("tournament-list");
        this.controller = controller;
        this.player = player;
    }

    public void execute(){
        TournamentList tournamentList = this.controller.getTournamentList(this.player);
        for(int i=0; i< tournamentList.getSize(); i++){
            Tournament tournament = tournamentList.getTournament(i);
            System.out.println("--> " + tournament.getName());
            ArrayList<Participant> participants = tournament.getParticipants();
            if (!participants.isEmpty())
                for(int j=0; j<participants.size(); j++)
                    System.out.println(participants.get(i).getID());
        }
    }
}
