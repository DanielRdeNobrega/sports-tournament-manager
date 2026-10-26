package views.commands.playerCommands;

import controllers.Controller;
import users.player.Player;
import views.commands.Command;

public class RemoveFromTournamentCommand extends Command {
    private Player player;
    public RemoveFromTournamentCommand(Controller controller, Player player){
        super("tournament-remove [Tournament-name][Participant-name]");
        this.controller = controller;
        this.player = player;
    }

    public void execute(){
        this.controller.removeFromTournament(this.commandArgument, this.player);
    }
}
