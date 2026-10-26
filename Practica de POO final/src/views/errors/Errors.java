package views.errors;

public enum Errors {
    COMMAND_NO_EXIST("Comando no reconocido"),
    NO_CORRECT("Usuario inválido"),
    NO_CORRECT_PARAMETERS("Numero de parámetros incompatibles"),
    MATCH_NO_EXIST("La tencnica de match no existe");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public void showError() {
        System.out.println(this.message);
    }
}
