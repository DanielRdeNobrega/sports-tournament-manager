package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class DeletePlayerCommand extends Command {
    private Admin admin;
    public DeletePlayerCommand(Controller controller, Admin admin){
        super("player-delete [DNI]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.deletePlayer(this.commandArgument, admin);
    }
}
