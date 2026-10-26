package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;

public class CreatePlayerCommand extends Command {
    private Admin admin;
    public CreatePlayerCommand(Controller controller, Admin admin){
        super("player-create [name];[surname];[DNI];[email];[password]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.createPlayer(this.commandArgument, this.admin);
    }
}
