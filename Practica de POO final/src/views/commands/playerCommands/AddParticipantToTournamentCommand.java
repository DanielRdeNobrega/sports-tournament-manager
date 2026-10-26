package views.commands.playerCommands;

import controllers.Controller;
import users.player.Player;
import views.commands.Command;

public class AddParticipantToTournamentCommand extends Command {
    private Player player;
    public AddParticipantToTournamentCommand(Controller controller, Player player){
        super("tournament-add [Tournament-name][Participant-name]");
        this.controller = controller;
        this.player = player;
    }

    public void execute(){
        this.controller.addToTournament(this.commandArgument, this.player);
    }
}
