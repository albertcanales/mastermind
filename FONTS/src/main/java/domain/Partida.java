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

    private String user;

    /**
     * Constructor per una nova partida
     * @author Albert Canales
     */
    Partida(String user) {
        this.temps = Duration.ZERO;
        this.user = user;
    }

    /**
     * Constructor per una partida pendent
     * @author Albert Canales
     */
    Partida(String user, Duration temps) {
        this.temps = temps;
        this.user = user;
    }

    // TODO Crec que aquest constructor es podrà esborrar finalment
    /**
     * Constructor per una partida finalitzada
     * @author Albert Canales
     */
    Partida(String user, Duration temps, LocalDateTime dataFi) {
        this.temps = temps;
        this.dataFi = dataFi;
        this.user = user;
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

    public String getUser() {
        return user;
    }
}
