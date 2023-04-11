package domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Classe que representa l'estat de la partida actual
 * @author Albert Canales
 */
class Partida {

    private Duration temps;

    /**
     * Constructor per una nova partida
     * @author Albert Canales
     */
    Partida() {
        this.temps = Duration.ZERO;
    }

    /**
     * Constructor per una partida pendent
     * @author Albert Canales
     */
    Partida(Duration temps) {
        this.temps = temps;
    }

    /**
     * Getter de temps
     * @author Albert Canales
     */
    Duration getTemps() {
        return temps;
    }

    /**
     * Mètode per afegir temps transcorregut a la partida
     * @param millis milisegons a afegir a la partida
     * @author Albert Canales
     */
    void addMillis(Long millis) {
        if(millis > 0)
            temps = temps.plusMillis(millis);
    }

}
