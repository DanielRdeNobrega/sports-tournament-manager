package views.commands;

import controllers.Controller;

public class LogoutCommands extends Command{
    public LogoutCommands(Controller controller){
        super("logout");
        this.controller = controller;
    }

    public void execute(){
        this.user = null;
    }
}
