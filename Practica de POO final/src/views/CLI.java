package views;

import users.Admin;
import users.User;
import users.player.Player;
import controllers.Controller;
import views.commands.Command;
import views.commands.LoginCommand;
import views.commands.LogoutCommands;
import views.commands.TournamentListCommand;
import views.commands.adminCommands.*;
import views.commands.playerCommands.*;
import static views.errors.Errors.COMMAND_NO_EXIST;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {
    private String title;
    private ArrayList<Command> commands;
    private boolean exit;
    private User user;
    private Controller controller;

    public CLI(){
        this.title = "GESTOR DEPORTIVO";
        this.exit = false;
        this.controller = new Controller();
        this.user = null;
        this.addCommands();
    }

    public void interact() {
        do {
            if(user!=null)
                this.user.addCoomands(this);
            else
                this.addCommands();
            this.execChoosedCommand();
        } while (!this.exit);
    }

    private void showCommands() {
        String string = "\nAYUDA\n";
        int length = string.length();
        for (int i = 0; i <length-2; i++) {
            string += "-";
        }
        System.out.println(string);
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("> " + commands.get(i).getTitle());
        }
        System.out.println("> exit");
    }

    public void showTitle() {
        String string = "\n" + this.title + "\n";
        for (int i = 0; i < this.title.length(); i++) {
            string += "-";
        }
        System.out.println(string);
    }

    private Command searchChoosedCommand(String title){
        for (int i = 0; i < commands.size(); i++)
            if (commands.get(i).getTitle().split(" ")[0].equalsIgnoreCase(title))
                return commands.get(i);
        return null;
    }

    private void execChoosedCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String commandArgument = scanner.nextLine();
        String[] parts = (commandArgument.split(" "));
        this.handleCommand(parts[0], commandArgument);
    }

    private void handleCommand(String commandName, String commandArgument) {
        if (commandName.equalsIgnoreCase("exit")) {
            this.exit = true;
        } else {
            executeCommand(commandName, commandArgument);
        }
    }

    private void executeCommand(String commandName, String commandArgument) {
        Command current = this.searchChoosedCommand(commandName);
        if (current != null) {
            current.setCommandArgument(commandArgument);
            current.execute();
            postCommandExecution(commandName, current);
        } else {
            COMMAND_NO_EXIST.showError();
            this.showCommands();
        }
    }

    private void postCommandExecution(String commandName, Command current) {
        if (commandName.equalsIgnoreCase("login")) {
            this.user = current.getUser();
            if (user != null) {
                user.addCoomands(this);
            }
        }
        else if(commandName.equalsIgnoreCase("logout")){
            this.user = current.getUser();
            this.addCommands();
        }
    }

    public void addCommands(Player player){
        ArrayList<Command>newCommands = new ArrayList<>();
        newCommands.add(new AddParticipantToTournamentCommand(this.controller, player));
        newCommands.add(new RemoveFromTournamentCommand(this.controller, player));
        newCommands.add(new ShowStatisticCommand(this.controller, player));
        newCommands.add(new TournamentListPlayerCommand(this.controller, player));
        newCommands.add(new LogoutCommands(this.controller));
        this.commands = newCommands;
    }

    public void addCommands(Admin admin){
        ArrayList<Command>newCommands = new ArrayList<>();
        newCommands.add(new AddPlayerToTeamCommand(this.controller, admin));
        newCommands.add(new CreatePlayerCommand(this.controller, admin));
        newCommands.add(new CreateTeamCommand(this.controller, admin));
        newCommands.add(new CreateTournamentCommand(this.controller, admin));
        newCommands.add(new DeletePlayerCommand(this.controller, admin));
        newCommands.add(new DeleteTeamCommand(this.controller, admin));
        newCommands.add(new DeleteTournamentCommand(this.controller, admin));
        newCommands.add(new RemovePlayerFromTeamCommand(this.controller, admin));
        newCommands.add(new TournamentListAdminCommand(this.controller, admin));
        newCommands.add(new TournamentMatchmakingCommand(this.controller, admin));
        newCommands.add(new LogoutCommands(this.controller));
        this.commands = newCommands;
    }

    public void addCommands(){
        ArrayList<Command>newCommands = new ArrayList<>();
        newCommands.add(new LoginCommand(this.controller));
        newCommands.add(new TournamentListCommand(this.controller));
        this.commands = newCommands;
    }
}