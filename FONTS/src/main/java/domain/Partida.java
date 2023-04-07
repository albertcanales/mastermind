package domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Classe que representa l'estat de la partida actual
 * @author Albert Canales
 */
class Partida {

    private Duration temps;
    private LocalDateTime dataFi;

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
     * Constructor per una partida finalitzada
     * @author Albert Canales
     */
    Partida(Duration temps, LocalDateTime dataFi) {
        this.temps = temps;
        this.dataFi = dataFi;
    }

    /**
     * Getter de temps
     * @author Albert Canales
     */
    Duration getTemps() {
        return temps;
    }

    /**
     * Setter de temps
     * @param temps nou valor per temps
     * @author Albert Canales
     */
    void setTemps(Duration temps) {
        this.temps = temps;
    }

    /**
     * Getter de dataFi
     * @author Albert Canales
     */
    LocalDateTime getDataFi() {
        return dataFi;
    }

    /**
     * Setter de dataFi
     * @param dataFi nou valor per dataFi
     * @author Albert Canales
     */
    void setDataFi(LocalDateTime dataFi) {
        this.dataFi = dataFi;
    }
}
