package views.errors;

public enum TournamentError {

    TOURNAMENT_NO_EXIST("El torneo no existe"),
    TOURNAMENT_EXIST("El torneo existe"),
    NO_TOURNAMENTS("No hay torneos"),
    NO_ENOUGH_PARTICIPANTS("Faltan participantes por emparejar"),
    EXIST_MATCH("Ya existe el match"),
    ACTIVE_TOURNAMENT("Actualmente el torneo está en progreso"),
    INACTIVE_TOURNAMENT("Actualmente el torneo no está en progreso"),
    PARTICIPANT_NO_EXIST("El participante no existe"),
    PLAYER_NO_EXISTS_IN_TOURNAMENT("El jugador no está dentro del torneo"),
    PLAYER_EXISTS_IN_TOURNAMENT("El jugador está dentro del torneo");

    private final String message;

    TournamentError(String message) {
        this.message = message;
    }

    public void showError() {
        System.out.println(this.message);
    }
}
