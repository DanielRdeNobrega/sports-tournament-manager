package views.errors;

public enum PlayerError {

    PLAYER_EXIST("ERROR jugador ya existente"),
    PLAYER_NO_EXIST("ERROR El jugador no existe"),
    EMPTY_LIST("ERROR La lista está vacía"),
    OVER_MAX_STATISTIC("Se deben inicializar exactamente todas las estadísticas del jugador"),
    STATISTIC_NO_EXIST("No existe la estadística");

    private final String message;

    PlayerError(String message) {
        this.message = message;
    }

    public void showError() {
        System.out.println(this.message);
    }
}
