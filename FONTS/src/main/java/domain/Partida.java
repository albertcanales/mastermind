package domain;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Classe que representa l'estat de la partida actual
 * @author Albert Canales
 */
public class Partida {

    Duration temps;
    LocalDateTime dataFi;

    /**
     * Constructor per una nova partida
     * @author Albert Canales
     */
    public Partida() {
        this.temps = Duration.ZERO;
    }

    /**
     * Constructor per una partida pendent
     * @author Albert Canales
     */
    public Partida(Duration temps) {
        this.temps = temps;
    }

    /**
     * Getter de temps
     * @author Albert Canales
     */
    public Duration getTemps() {
        return temps;
    }

    /**
     * Setter de temps
     * @param temps nou valor per temps
     * @author Albert Canales
     */
    public void setTemps(Duration temps) {
        this.temps = temps;
    }

    /**
     * Getter de dataFi
     * @author Albert Canales
     */
    public LocalDateTime getDataFi() {
        return dataFi;
    }

    /**
     * Setter de dataFi
     * @param dataFi nou valor per dataFi
     * @author Albert Canales
     */
    public void setDataFi(LocalDateTime dataFi) {
        this.dataFi = dataFi;
    }
}
