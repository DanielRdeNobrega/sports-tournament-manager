import views.CLI;

public class SportsManager {
    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.showTitle();
        cli.interact();
    }
}