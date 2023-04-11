package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;
import persistance.ControladorPersistencia;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Controlador de la capa de domini
 * @author Albert Canales
 */
public class ControladorDomini {

    private final ControladorPartida controladorPartida;
    private ControladorPersistencia controladorPersistencia;

    private User user;

    /**
     * Constructor del Controlador de Domini
     * @author Albert Canales
     */
    ControladorDomini() {
        controladorPartida = new ControladorPartida();
    }

    public Boolean existsUser(String username) {
        return controladorPersistencia.existsUser(username);
    }

    public Boolean loginUser(String username, String password) {
        String hash = controladorPersistencia.getPasswordHash(username);
        if(!Objects.equals(hash, getSHA256(password)))
            return false;
        String name = controladorPersistencia.getUserName(username);
        user = new User(username, name);
        return true;
    }

    public void registerUser(String username, String name, String password) {
        controladorPersistencia.registerUser(username, name, password);
    }

    /**
     * Mètode per iniciar una nova partida amb el jugador com a maker
     * @param solucio solucio de la partida
     * @param algorisme enter que representa l'algorisme escollit per al breaker
     * @author Albert Canales
     */
    public void novaPartidaMaker(List<Integer> solucio, Integer algorisme) {
        controladorPartida.novaPartidaMaker(solucio, algorisme, user.getUsername());
    }

    /**
     * Mètode per iniciar una nova partida amb el jugador com a breaker
     * @param nivellDificultat nivell de dificultat escollit pel jugador
     * @author Albert Canales
     */
    public void novaPartidaBreaker(Integer nivellDificultat) {
        controladorPartida.novaPartidaBreaker(nivellDificultat, user.getUsername());
    }

    /**
     * Mètode per comprovar si existeix una partida guardada i és de l'usuari registrat
     * @return Booleà indicant si existeix una partida guardada
     * @author Albert Canales
     */
    public Boolean existsPartidaGuardada() {
        if(controladorPersistencia.existsPartidaGuardada()) {
            String usernameGuardada = controladorPersistencia.getUserPartidaGuardada();
            return Objects.equals(usernameGuardada, user.getUsername());
        }
        return false;
    }

    /**
     * Mètode per carregar la partida guardada
     * @author Albert Canales
     */
    public void carregarPartida() {
        Integer nivellDificultat = controladorPersistencia.getNivellDificultatPartidaGuardada();
        List<List<Integer>> intents = controladorPersistencia.getIntentsPartidaGuardada();
        List<List<Integer>> feedback = controladorPersistencia.getFeedbackPartidaGuardada();
        List<Integer> solucio = controladorPersistencia.getSolucioPartidaGuardada();
        String username = user.getUsername();
        if(controladorPersistencia.isBreakerPartidaGuardada())
            this.carregarPartidaBreaker(nivellDificultat, intents, feedback, solucio, username);
        else
            this.carregarPartidaMaker(nivellDificultat, intents, feedback, solucio, username);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és breaker
     * @author Albert Canales
     */
    private void carregarPartidaBreaker(Integer nivellDificultat, List<List<Integer>> intents,
                                        List<List<Integer>> feedback, List<Integer> solucio, String username) {
        Duration temps = controladorPersistencia.getTempsPasrtidaGuardada();
        controladorPartida.carregarPartidaBreaker(nivellDificultat, intents, feedback, solucio, temps, username);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és maker
     * @author Albert Canales
     */
    private void carregarPartidaMaker(Integer nivellDificultat, List<List<Integer>> intents,
                                      List<List<Integer>> feedback, List<Integer> solucio, String username) {;
        controladorPartida.carregarPartidaMaker(nivellDificultat, intents, feedback, solucio, username);
    }

    /**
     * Getter del rànquing de les millors partides d'una dificultat concreta
     * @param nivellDificultat nombre del nivell de dificultat
     * @param nombrePartides nombre de partides a mostrar
     * @return Una llista que conté tuples amb (username: String, intents: Integer, temps: Duration)
     * @author Albert Canales
     */
    public List<List<Object>> getRanquing(Integer nivellDificultat, Integer nombrePartides) {
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getPersonalRecord() {
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getTimePlayed() {
        return null;
    }

    /**
     * Getter de victòries i derrotes
     * @return Una llista amb parelles (nombre de victòries, nombre de derrotes) per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<List<Integer>> getWinLost() {
        return null;
    }

    /**
     * Getter de ratxa de victòries
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWinstreak() {
        return null;
    }

    /**
     * Getter de mitjana d'intents com a breaker
     * @return Una llista amb la mitjana d'intents per a cada dificultat
     * @author Albert Canales
     */
    public List<Double> getAverageAsBreaker() {
        return null;
    }

    /**
     * Getter de mitjana d'intents com a maker
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada algorisme
     * @author Albert Canales
     */
    public List<Double> getAverageAsMaker() {
        return null;
    }

    /**
     * Getter de la solució de la partida actual
     * @return La seqüència solució de la partida actual
     * @author Albert Canales
     */
    public List<Integer> getSolucio() {
        return controladorPartida.getSequenciaSolucio();
    }

    /**
     * Getter de tots els intents de la partida actual
     * @return Una llista amb tots els intents de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getIntents() {
        return controladorPartida.getIntents();
    }

    /**
     * Getter de tots els feedbacks de la partida actual
     * @return Una llista amb tots els feedbacks de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getFeedbacks() {
        return controladorPartida.getFeedbacks();
    }

    /**
     * Mètode per a col·locar una bola en l'intent actual de la partida actual
     * @param index La posició on es vol col·locar la bola
     * @param bola La bola que es vol col·locar
     * @throws DomainException si bola no és una Bola vàlida
     * @author Albert Canales
     */
    public void setBola(Integer index, Integer bola) throws DomainException {
        controladorPartida.setBola(index, bola);
    }

    /**
     * Mètode per a validar l'intent actual en la partida actual
     * @return El feedback corresponent a l'intent donat
     * @author Albert Canales
     */
    public List<Integer> validarSequencia() {
        List<Integer> feedback = controladorPartida.validarSequencia();
        if(controladorPartida.isPartidaAcabada()) {
            // TODO Acabar partida (DataFi, estadístiques usuari, etc)
        }
        return feedback;
    }

    /**
     * Mètode perquè el bot jugui la partida
     * @author Albert Canales
     */
    void botSolve() {
        controladorPartida.botSolve();
    }

    private String getSHA256(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(value.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return String.format("%064x", new BigInteger(1, md.digest()));
    }
}
