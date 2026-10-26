package views.commands;
import controllers.Controller;
import static views.errors.Errors.NO_CORRECT;

public class LoginCommand extends Command{
    public LoginCommand(Controller controller){
        super("login [email][password]");
        this.controller = controller;
    }

    public void execute(){
        this.user = this.controller.searchUser(this.commandArgument);
        if(this.user == null)
            NO_CORRECT.showError();
    }
}
