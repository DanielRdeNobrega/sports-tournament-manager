package views.commands;
import controllers.Controller;
import users.User;

public abstract class Command {
    private String title;
    protected Controller controller;
    protected String commandArgument;
    protected User user;

    public Command(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public void setCommandArgument(String commandArgument) {
        this.commandArgument = commandArgument;
    }
    public abstract void execute();

}
