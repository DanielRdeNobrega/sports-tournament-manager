package views.commands;

import controllers.Controller;
import tournaments.Participant;
import tournaments.Tournament;
import tournaments.TournamentList;

import java.util.ArrayList;

public class TournamentListCommand extends Command{
    public TournamentListCommand(Controller controller){
        super("tournament-list");
        this.controller = controller;
    }

    public void execute(){
        TournamentList tournamentList = this.controller.getTournamentList();
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
