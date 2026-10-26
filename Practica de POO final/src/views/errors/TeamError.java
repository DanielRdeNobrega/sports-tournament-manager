package views.errors;

public enum TeamError {

    EMPTY_LIST("ERROR La lista de equipos está vacía"),
    ALREADY_IN("El jugador ya está en el equipo"),
    ALREADY_OUT("El jugador no está en el equipo"),
    MIN_MATES("Un equipo debe tener mas de dos jugadores"),
    TEAM_EXISTS("El equipo ya existe"),
    TEAM_NO_EXISTS("El equipo no existe"),
    STATISTIC_NO_EXIST("No existe la estadística");

    private final String message;

    TeamError(String message) {
        this.message = message;
    }

    public void showError() {
        System.out.println(this.message);
    }
}
