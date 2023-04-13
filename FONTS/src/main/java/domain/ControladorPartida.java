package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidPartidaTypeException;
import domain.exceptions.NotPlayingPartidaException;
import domain.exceptions.PartidaAlreadyFinished;

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
     * Mètode per saber si s'està jugant una partida actualment
     * @author Albert Canales
     */
    Boolean isPartidaPresent() {
        return partida != null;
    }

    /**
     * Mètode que crea una nova partida actual on el jugador és el maker
     * @param solucio solució de la partida proporcionada pel jugador
     * @param algorisme enter que representa l'algorisme escollit com a breaker
     * @throws DomainException si el tamany de list no és correcte
     * @throws DomainException si algorisme no és vàlid
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
     * @throws DomainException si el nivellDificultat no és vàlid
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
     *
     * @param username
     * @param intents  intents de la partida existent
     * @param feedback feedbacks de la partida existent
     * @param solucio  solucio de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws DomainException si el nivellDificultat no és vàlid
     * @author Albert Canales
     */
    void carregarPartidaMaker(List<List<Integer>> intents, List<List<Integer>> feedback,
                              List<Integer> solucio) throws DomainException {
        partida = new Partida();
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(solucio, intents, feedback);
        dificultat = null;
        botBreaker = null;
    }

    /**
     * Mètode que carrega una partida pendent on el jugador feia de breaker
     *
     * @param username
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @param intents          intents de la partida existent
     * @param feedback         feedbacks de la partida existent
     * @param solucio          solucio de la partida existent
     * @param temps            temps transcorregut de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws DomainException si el nivellDificultat no és vàlid
     * @author Albert Canales
     */
    void carregarPartidaBreaker(Integer nivellDificultat, List<List<Integer>> intents, List<List<Integer>> feedback,
                                List<Integer> solucio, Long temps) throws DomainException {
        partida = new Partida(Duration.ofMillis(temps));
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(solucio, intents, feedback);
        dificultat = Dificultat.create(nivellDificultat);
        botBreaker = null;
    }

    /**
     * Mètode deixar la partida actual
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    void sortirPartida() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        partida = null;
        taulell = null;
        dificultat = null;
        botMaker = null;
        botBreaker = null;
    }

    /**
     * Mètode per validar l'últim intent de la partida i passar al següent
     * @return feedback de l'últim intent, en funció de la dificultat de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si on la partida que es juga el jugador no es breaker
     * @throws DomainException si el tamany de feedback no és correcte
     * @author Albert Canales
     */
    List<Integer> validarSequencia() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        if(isPartidaAcabada())
            throw new PartidaAlreadyFinished();
        List<Integer> ultimIntent = taulell.getUltimIntent();
        List<Integer> solucio = taulell.getSolucio();

        List<Integer> feedback = dificultat.validarSequencia(solucio, ultimIntent);

        taulell.addFeedback(feedback);
        return feedback;
    }

    /**
     * Mètode perquè el bot jugui la partida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si on la partida que es juga el bot no es breaker
     * @author Albert Canales
     */
    void botSolve() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(isJugadorBreaker())
            throw new InvalidPartidaTypeException("Maker");
        if(isPartidaAcabada())
            throw new PartidaAlreadyFinished();
        List<Integer> solution = taulell.getSolucio();
        botBreaker.solve(new ArrayList<>(solution));
    }

    /**
     * Getter per al temps transcorregut de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @return el temps transcorregut amb mil·lisegons
     * @author Albert Canales
     */
    Long getTempsMillis() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return partida.getTemps().toMillis();
    }

    /**
     * Mètode per afegir temps transcorregut a la partida
     * @throws DomainException si no s'està jugant cap partida
     * @param millis temps a afegir, amb mil·lisegons
     * @author Albert Canales
     */
    void addTempsMillis(Long millis) throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        partida.addMillis(millis);
    }

    /**
     * Getter del nombre de boles d'una seqüència
     * @author Albert Canales
     */
    static Integer getNumBoles() {
        return Taulell.NUMBOLES;
    }

    /**
     * Getter del nivell de dificultat de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si la partida que es juga el jugador no és breaker
     * @return nombre de la dificultat corresponent
     * @author Albert Canales
     */
    Integer getNivellDificultat() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        return dificultat.getNivellDificultat().number();
    }

    /**
     * Getter de l'algorisme amb el qual juga el bot
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si la partida que es juga el bot no és breaker
     * @return nombre de l'algorisme corresponent
     * @author Albert Canales
     */
    Integer getAlgorisme() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(isJugadorBreaker())
            throw new InvalidPartidaTypeException("Maker");
        return botBreaker.getTipusAlgorisme().number();
    }

    /**
     * Getter de la solució de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    List<Integer> getSequenciaSolucio() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getSolucio();
    }

    /**
     * Getter del número d'intents de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Integer getNumIntents() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getNumeroIntent();
    }

    /**
     * Getter dels intents de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    List<List<Integer>> getIntents() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getIntents();
    }

    /**
     * Getter dels feedbacks de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    List<List<Integer>> getFeedbacks() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getFeedbacks();
    }

    /**
     * Mètode per saber si el jugador fa de breaker en la partida actual
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Boolean isJugadorBreaker() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return botMaker != null;
    }

    /**
     * Mètode per saber si una partida està guanyada
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Boolean isPartidaGuanyada() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        List<Integer> ultimFeedback = taulell.getUltimFeedback();
        for (Integer bola : ultimFeedback) {
            if (!bola.equals(Bola.NEGRE.number()))
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Mètode per saber si una partida està perduda
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Boolean isPartidaPerduda() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getNumeroIntent() >= Taulell.NUMINTENTS;
    }

    /**
     * Mètode per saber si una partida està acabada
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Boolean isPartidaAcabada() throws DomainException {
        return isPartidaPerduda() || isPartidaGuanyada();
    }

    /**
     * Mètode per saber si l'últim intent està ple
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    Boolean isUltimIntentPle() throws DomainException {
        return taulell.isUltimIntentPle();
    }

    /**
     * Mètode per col·locar una bola en la posició indicada de l'intent actual
     * @param index posició de la bola
     * @param bola bola a col·locar
     * @throws DomainException si bola no és una Bola vàlida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si en la partida actual el jugador no és breaker
     * @author Albert Canales
     */
    void setBola(Integer index, Integer bola) throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        taulell.setBola(index, bola);
    }
}
