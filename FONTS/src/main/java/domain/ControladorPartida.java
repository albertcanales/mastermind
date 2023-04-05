package domain;

import java.time.Duration;
import java.util.List;

public class ControladorPartida {

    Partida partida;
    Dificultat dificultat;
    Taulell taulell;

    /**
     * Constructor del controlador per a carregar una nova partida
     * @param nivellDificultat nombre corresponent a la dificultat de la partida
     * @author Albert Canales
     */
    ControladorPartida(Integer nivellDificultat) {
        partida = new Partida();
        taulell = new Taulell();
        // TODO Falta la dificultat
    }

    /**
     * Constructor del controlador per a carregar una partida existent
     * @param nivellDificultat nombre corresponent a la dificultat de la partida
     * @param feedbacks llista de les seqüències de feedback de la partida prèvies
     * @param intents llista dels intents previs de la partida previs
     * @param temps duració (en mil·lisegons) actual de la partida
     * @param temps duració (en mil·lisegons) actual de la partida
     * @author Albert Canales
     */
    ControladorPartida(Integer nivellDificultat, List<List<Integer>> feedbacks, List<List<Integer>> intents, Long temps) {
        this(nivellDificultat);

        // TODO Falta el constructor adient per a taulell
        partida.setTemps(Duration.ofMillis(temps));
    }

    /**
     * Mètode per validar l'últim intent de la partida i passar al següent
     * @return feedback de l'últim intent, en funció de la dificultat de la partida
     * @author Albert Canales
     */
    List<Integer> validarSequencia() {
        List<Integer> ultimIntent = taulell.getUltimIntent();
        List<Integer> solucio = taulell.getSolucio();

        List<Integer> feedback = dificultat.validarSequencia(solucio, ultimIntent);

        taulell.addFeedback(feedback);
        return feedback;
    }

    /**
     * Getter per al temps transcorregut de la partida
     * @return el temps transcorregut amb mil·lisegons
     * @author Albert Canales
     */
    Long getTemps() {
        return partida.getTemps().toMillis();
    }

    /**
     * Getter del nivell de dificultat de la partida
     * @return nombre de la dificultat corresponent
     * @author Albert Canales
     */
    Integer getNivellDificultat() {
        return dificultat.getNivellDificultat().number();
    }

    /**
     * Getter de la solució de la partida
     * @author Albert Canales
     */
    List<Integer> getSequenciaSolucio() {
        return taulell.getSolucio();
    }

    /**
     * Mètode per col·locar una bola en la posició indicada de l'intent actual
     * @param index posició de la bola
     * @param bola bola a col·locar
     * @author Albert Canales
     */
    void setBola(Integer index, Integer bola) {
        taulell.setBola(index, bola);
    }
}
