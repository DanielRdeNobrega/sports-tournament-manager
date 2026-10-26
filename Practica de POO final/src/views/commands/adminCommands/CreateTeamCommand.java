package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class CreateTeamCommand extends Command {
    private Admin admin;
    public CreateTeamCommand(Controller controller, Admin admin){
        super("team-create [DNI/DNIS(separados por punto y coma)];[Team-Name]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.createTeam(this.commandArgument, this.admin);
    }
}
