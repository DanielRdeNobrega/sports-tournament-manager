package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class TournamentMatchmakingCommand extends Command {
    private Admin admin;
    public TournamentMatchmakingCommand(Controller controller, Admin admin){
        super("tournament-matchmaking [-m/-a];[Tournament-name](;[][])");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.matchMaking(this.commandArgument, this.admin);
    }
}
