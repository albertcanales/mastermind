package domain;

import domain.exceptions.DomainException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la partida actual
 * @author Albert Canales
 */
class ControladorPartida {

    private Partida partida;
    private Dificultat dificultat;
    private Taulell taulell;
    private BotBreaker botBreaker;
    private BotMaker botMaker;

    /**
     * Mètode que crea una nova partida actual on el jugador és el maker
     * @param solucio solució de la partida proporcionada pel jugador
     * @param algorisme enter que representa l'algorisme escollit com a breaker
     * @throws DomainException si el tamany de list no és correcte
     * @author Albert Canales
     */
    void novaPartidaMaker(List<Integer> solucio, Integer algorisme) throws DomainException {
        partida = new Partida();
        taulell = new Taulell(solucio);
        botBreaker = BotBreaker.create(algorisme);
        botMaker = null;
        dificultat = null;
    }

    /**
     * Mètode que crea una nova partida actual on el jugador és el breaker
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @throws DomainException si el tamany de list no és correcte
     * @author Albert Canales
     */
    void novaPartidaBreaker(Integer nivellDificultat) throws DomainException {
        partida = new Partida();
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(botMaker.generaSequenciaSolucio());
        dificultat = Dificultat.create(nivellDificultat);
        botBreaker = null;
    }

    /**
     * Mètode que carrega una partida pendent on el jugador feia de maker
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @param intents intents de la partida existent
     * @param feedback feedbacks de la partida existent
     * @param solucio solucio de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    void carregarPartidaMaker(Integer nivellDificultat, List<List<Integer>> intents, List<List<Integer>> feedback,
                              List<Integer> solucio) throws DomainException {
        partida = new Partida();
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(solucio, intents, feedback);
        dificultat = Dificultat.create(nivellDificultat);
        botBreaker = null;
    }

    /**
     * Mètode que carrega una partida pendent on el jugador feia de breaker
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @param intents intents de la partida existent
     * @param feedback feedbacks de la partida existent
     * @param solucio solucio de la partida existent
     * @param temps temps transcorregut de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    void carregarPartidaBreaker(Integer nivellDificultat, List<List<Integer>> intents, List<List<Integer>> feedback,
                                List<Integer> solucio, Duration temps) throws DomainException {
        partida = new Partida(temps);
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(solucio, intents, feedback);
        dificultat = Dificultat.create(nivellDificultat);
        botBreaker = null;
    }

    /**
     * Mètode per validar l'últim intent de la partida i passar al següent
     * @return feedback de l'últim intent, en funció de la dificultat de la partida
     * @throws DomainException si el tamany de feedback no és correcte
     * @author Albert Canales
     */
    List<Integer> validarSequencia() throws DomainException {
        List<Integer> ultimIntent = taulell.getUltimIntent();
        List<Integer> solucio = taulell.getSolucio();

        List<Integer> feedback = dificultat.validarSequencia(solucio, ultimIntent);

        taulell.addFeedback(feedback);
        return feedback;
    }

    /**
     * Mètode perquè el bot jugui la partida
     * @author Albert Canales
     */
    void botSolve() {
        List<Integer> solution = taulell.getSolucio();
        botBreaker.solve(new ArrayList<>(solution));
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
     * Getter dels intents de la partida
     * @author Albert Canales
     */
    List<List<Integer>> getIntents() {
        return taulell.getIntents();
    }

    /**
     * Getter dels feedbacks de la partida
     * @author Albert Canales
     */
    List<List<Integer>> getFeedbacks() {
        return taulell.getFeedbacks();
    }

    /**
     * Mètode per saber si una partida està guanyada
     * @author Albert Canales
     */
    Boolean isPartidaGuanyada() {
        List<Integer> ultimFeedback = taulell.getUltimFeedback();
        for (Integer bola : ultimFeedback) {
            if (!bola.equals(Bola.NEGRE.number()))
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Mètode per saber si una partida està perduda
     * @author Albert Canales
     */
    Boolean isPartidaPerduda() {
        return taulell.getNumeroIntent() >= Taulell.NUMINTENTS;
    }

    /**
     * Mètode per saber si una partida està acabada
     * @author Albert Canales
     */
    Boolean isPartidaAcabada() {
        return isPartidaPerduda() || isPartidaGuanyada();
    }

    /**
     * Mètode per col·locar una bola en la posició indicada de l'intent actual
     * @param index posició de la bola
     * @param bola bola a col·locar
     * @throws DomainException si bola no és una Bola vàlida
     * @author Albert Canales
     */
    void setBola(Integer index, Integer bola) throws DomainException {
        taulell.setBola(index, bola);
    }
}
