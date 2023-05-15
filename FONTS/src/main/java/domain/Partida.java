package domain;

import java.time.Duration;

/**
 * Classe que representa l'estat de la partida actual
 * @author Albert Canales
 */
class Partida {

    private Duration temps;
    private Boolean solucioVista;

    /**
     * Constructor per una nova partida
     */
    Partida() {
        this.temps = Duration.ZERO;
        this.solucioVista = false;
    }

    /**
     * Constructor per una partida pendent
     */
    Partida(Duration temps, Boolean solucioVista) {
        this.temps = temps;
        this.solucioVista = solucioVista;
    }

    /**
     * Getter de temps
     */
    Duration getTemps() {
        return temps;
    }

    /**
     * Mètode per afegir temps transcorregut a la partida
     * @param millis milisegons a afegir a la partida
     */
    void addMillis(Long millis) {
        if(millis > 0)
            temps = temps.plusMillis(millis);
    }

    /**
     * Getter de la solució vista
     */
    public Boolean isSolucioVista() {
        return solucioVista;
    }

    /**
     * Mètode per assignar la solució com a vista
     */
    public void veureSolucio() {
        solucioVista = true;
    }
}
