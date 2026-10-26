package views.commands.adminCommands;

import controllers.Controller;
import tournaments.Participant;
import tournaments.Tournament;
import tournaments.TournamentList;
import users.Admin;
import views.commands.Command;

import java.util.ArrayList;

public class TournamentListAdminCommand extends Command {
    private Admin admin;
    public TournamentListAdminCommand(Controller controller, Admin admin){
        super("tournament-list");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        TournamentList tournamentList =this.controller.getTournamentList(this.admin);
        for(int i=0; i< tournamentList.getSize(); i++){
            Tournament tournament = tournamentList.getTournament(i);
            System.out.println("--> " + tournament.getName());
            ArrayList<Participant>participants = tournament.getParticipants();
            if (!participants.isEmpty())
                for(int j=0; j<participants.size(); j++)
                    System.out.println(participants.get(i).getID());
        }
    }
}
