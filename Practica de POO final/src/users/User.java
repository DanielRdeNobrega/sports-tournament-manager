package users;

import views.CLI;

public  abstract class User {
    private String email;
    private String password;
    private String DNI;

    public User(String email, String password, String DNI){
        this.email = email;
        this.password = password;
        this.DNI = DNI;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public abstract void addCoomands(CLI cli);
    public String getID() {
        return DNI;
    }
}
