package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidPartidaTypeException;
import exceptions.domain.NotPlayingPartidaException;
import exceptions.domain.PartidaAlreadyFinished;

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
     */
    void novaPartidaMaker(List<Integer> solucio, Integer algorisme) throws DomainException {
        partida = new Partida();
        taulell = new Taulell(solucio);
        botBreaker = BotBreaker.create(algorisme);
        botMaker = null;
        dificultat = new DificultatMitja();
    }

    /**
     * Mètode que crea una nova partida actual on el jugador és el breaker
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @throws DomainException si el tamany de list no és correcte
     * @throws DomainException si el nivellDificultat no és vàlid
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
     * @param intents  intents de la partida existent
     * @param feedbacks feedbacks de la partida existent
     * @param solucio  solucio de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws DomainException si el nivellDificultat no és vàlid
     */
    void carregarPartidaMaker(Integer algorisme, List<List<Integer>> intents, List<List<Integer>> feedbacks,
                              List<Integer> solucio) throws DomainException {
        partida = new Partida();
        botMaker = null;
        taulell = new Taulell(solucio, intents, feedbacks);
        dificultat = null;
        botBreaker = BotBreaker.create(algorisme);
    }

    /**
     * Mètode que carrega una partida pendent on el jugador feia de breaker
     * @param nivellDificultat enter que representa el nivell de dificultat de la partida
     * @param intents          intents de la partida existent
     * @param feedback         feedbacks de la partida existent
     * @param solucio          solucio de la partida existent
     * @param temps            temps transcorregut de la partida existent
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws DomainException si el nivellDificultat no és vàlid
     */
    void carregarPartidaBreaker(Integer nivellDificultat, List<List<Integer>> intents, List<List<Integer>> feedback,
                                List<Integer> solucio, Long temps, Boolean solucioVista) throws DomainException {
        partida = new Partida(Duration.ofMillis(temps), solucioVista);
        botMaker = new BotMaker(Taulell.NUMBOLES, Bola.numColors());
        taulell = new Taulell(solucio, intents, feedback);
        dificultat = Dificultat.create(nivellDificultat);
        botBreaker = null;
    }

    /**
     * Mètode deixar la partida actual
     * @throws DomainException si no s'està jugant cap partida
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
     */
    void botSolve() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(isJugadorBreaker())
            throw new InvalidPartidaTypeException("Maker");
        if(isPartidaAcabada())
            throw new PartidaAlreadyFinished();

        List<Integer> solution = taulell.getSolucio();
        List<List<Integer>> intents = botBreaker.solve(new ArrayList<>(solution));

        List<List<Integer>> feedbacks = new ArrayList<>();
        for (List<Integer> intent : intents)
            feedbacks.add(dificultat.validarSequencia(solution, intent));
        List<Integer> lastIntent = new ArrayList<>();
        for (int i = 0; i < Taulell.NUMBOLES; i++)
            lastIntent.add(0);
        intents.add(lastIntent);

        taulell = new Taulell(solution, intents, feedbacks);
    }

    /**
     * Getter per al temps transcorregut de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @return el temps transcorregut amb mil·lisegons
     */
    Long getTempsMillis() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        return partida.getTemps().toMillis();
    }

    /**
     * Mètode per afegir temps transcorregut a la partida
     * @throws DomainException si no s'està jugant cap partida
     * @param millis temps a afegir, amb mil·lisegons
     */
    void addTempsMillis(Long millis) throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        partida.addMillis(millis);
    }

    /**
     * Getter del nombre de boles d'una seqüència
     */
    static Integer getNumBoles() {
        return Taulell.NUMBOLES;
    }

    /**
     * Getter del nivell de dificultat de la partida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si la partida que es juga el jugador no és breaker
     * @return nombre de la dificultat corresponent
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
     */
    List<Integer> getSequenciaSolucio() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getSolucio();
    }

    /**
     * Getter del número d'intents de la partida
     * @throws DomainException si no s'està jugant cap partida
     */
    Integer getNumIntents() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getNumeroIntent();
    }

    /**
     * Getter dels intents de la partida
     * @throws DomainException si no s'està jugant cap partida
     */
    List<List<Integer>> getIntents() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getIntents();
    }

    /**
     * Getter dels feedbacks de la partida
     * @throws DomainException si no s'està jugant cap partida
     */
    List<List<Integer>> getFeedbacks() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getFeedbacks();
    }

    /**
     * Mètode per saber si el jugador fa de breaker en la partida actual
     * @throws DomainException si no s'està jugant cap partida
     */
    Boolean isJugadorBreaker() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return botMaker != null;
    }

    /**
     * Mètode per saber si una partida està guanyada
     * @throws DomainException si no s'està jugant cap partida
     */
    Boolean isPartidaGuanyada() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        List<Integer> ultimFeedback = taulell.getUltimFeedback();
        if(ultimFeedback == null) return Boolean.FALSE;
        for (Integer bola : ultimFeedback) {
            if (!bola.equals(Bola.NEGRE.number()))
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Mètode per saber si una partida està perduda
     * @throws DomainException si no s'està jugant cap partida
     */
    Boolean isPartidaPerduda() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        return taulell.getNumeroIntent() >= Taulell.NUMINTENTS || partida.isSolucioVista();
    }

    /**
     * Mètode per saber si una partida està acabada
     * @throws DomainException si no s'està jugant cap partida
     */
    Boolean isPartidaAcabada() throws DomainException {
        return isPartidaPerduda() || isPartidaGuanyada();
    }

    /**
     * Mètode per saber si l'últim intent està ple
     */
    Boolean isUltimIntentPle() {
        return taulell.isUltimIntentPle();
    }

    /**
     * Mètode per col·locar una bola en la posició indicada de l'intent actual
     * @param index posició de la bola
     * @param bola bola a col·locar
     * @throws DomainException si bola no és una Bola vàlida
     * @throws DomainException si no s'està jugant cap partida
     * @throws DomainException si en la partida actual el jugador no és breaker
     */
    void setBola(Integer index, Integer bola) throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        if(!isJugadorBreaker())
            throw new InvalidPartidaTypeException("Breaker");
        taulell.setBola(index, bola);
    }

    /**
     * Mètode per saber si uns intents i feedbacks donats són vàlids
     */
    Boolean isValidIntentsFeedbacks(List<List<Integer>> intents, List<List<Integer>> feedbacks) {
        return Taulell.isValidIntentsFeedbacks(intents, feedbacks);
    }

    /**
     * Mètode per assignar la solució com a vista
     * @throws DomainException si no s'està jugant cap partida
     */
    public void veureSolucio() throws DomainException {
        if(!isPartidaPresent())
            throw new NotPlayingPartidaException();
        partida.veureSolucio();
    }
}
