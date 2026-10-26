package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class AddPlayerToTeamCommand extends Command {
    private Admin admin;
    public AddPlayerToTeamCommand(Controller controller, Admin admin){
        super("team-add [DNI];[Team-Name]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.addToTeam(this.commandArgument, admin);
    }
}
