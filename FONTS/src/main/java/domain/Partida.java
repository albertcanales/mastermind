package domain;

import java.time.Duration;

/**
 * Classe que representa l'estat de la partida actual
 * @author Albert Canales
 */
class Partida {

    private Duration temps;

    /**
     * Constructor per una nova partida
     */
    Partida() {
        this.temps = Duration.ZERO;
    }

    /**
     * Constructor per una partida pendent
     */
    Partida(Duration temps) {
        this.temps = temps;
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

}
