package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class DeleteTournamentCommand extends Command {
    private Admin admin;
    public DeleteTournamentCommand(Controller controller, Admin admin){
        super("tournament-remove [Tournament-name]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.deleteTournament(this.commandArgument, this.admin);
    }
}
