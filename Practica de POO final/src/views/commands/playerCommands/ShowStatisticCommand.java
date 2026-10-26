package views.commands.playerCommands;

import controllers.Controller;
import users.player.Player;
import users.player.Statistic;
import views.commands.Command;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowStatisticCommand extends Command {
    private Player player;
    private final int NUM_ARGUMENTS = 2;
    public ShowStatisticCommand(Controller controller, Player player){
        super("statistics-show [–csv/-json]");
        this.controller = controller;
        this.player = player;
    }

    public void execute(){
        Statistic [] statistics = this.controller.getStatistics(this.player);
        String[] command = (this.commandArgument.split(" "));
        if (command.length == NUM_ARGUMENTS) {

            if (command[1].equalsIgnoreCase("-csv")) {
                String headers = "Title,Value";
                String body = Stream.of(statistics)
                        .map(stat -> stat.getName() + "," + stat.getStatistic())
                        .collect(Collectors.joining("\n"));
                String csv = headers + "\n" + body;
                System.out.println(csv);
            }
            else if (command[1].equalsIgnoreCase("-json")) {
                System.out.println("{");
                for (int i = 0; i < statistics.length; i++) {
                    System.out.println("  \"" + statistics[i].getName() + "\": " + statistics[i].getStatistic());
                    if (i < statistics.length - 1) {
                        System.out.println(",");
                    }
                }

                System.out.println("}");
            }
        }
    }
}
