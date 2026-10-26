package views.commands.adminCommands;

import controllers.Controller;
import users.Admin;
import views.commands.Command;


public class CreateTournamentCommand extends Command {
    private Admin admin;
    public CreateTournamentCommand(Controller controller, Admin admin){
        super("tournament-create [name][begin-date DD/MM/YYYY][end-date DD/MM/YYYY][league][sport][statistic]");
        this.controller = controller;
        this.admin = admin;
    }

    public void execute(){
        this.controller.createTournament(this.commandArgument, this.admin);
    }
}
