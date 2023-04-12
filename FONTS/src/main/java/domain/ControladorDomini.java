package domain;

import domain.exceptions.*;
import persistance.ControladorPersistencia;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Controlador de la capa de domini
 * @author Albert Canales
 */
public class ControladorDomini {

    private final ControladorPartida controladorPartida;
    private final ControladorPersistencia controladorPersistencia;

    private User user;

    /**
     * Constructor del Controlador de Domini
     * @author Albert Canales
     */
    ControladorDomini() {
        controladorPartida = new ControladorPartida();
        controladorPersistencia = new ControladorPersistencia();
        user = null;
    }

    /**
     * Mètode per comprovar si existeix un usuari
     * @param username username de l'usuari a comprovar
     * @return si l'usuari donat existeix
     * @author Albert Canales
     */
    public Boolean existsUser(String username) {
        return controladorPersistencia.existsUser(username);
    }

    /**
     * Mètode per iniciar sessió d'un usuari existent
     * @param username username del l'usuari
     * @param password contrasenya de l'usuari
     * @throws UserNotExistsExeption si l'usuari no existeix
     * @return si la contrasenya donada és correcta
     * @author Albert Canales
     */
    public Boolean loginUser(String username, String password) throws DomainException {
        if(!existsUser(username))
            throw new UserNotExistsExeption(username);
        String hash = controladorPersistencia.getPasswordHash(username);
        if(!Objects.equals(hash, User.getPasswordHash(password)))
            return false;
        String name = controladorPersistencia.getUserName(username);
        List<Integer> personalRecord = controladorPersistencia.getUserPersonalRecord(username);
        List<Integer> timePlayed = controladorPersistencia.getUserTimePlayed(username);
        List<Integer> wonGames = controladorPersistencia.getUserWonGames(username);
        List<Integer> lostGames = controladorPersistencia.getUserLostGames(username);
        List<Integer> winStreak = controladorPersistencia.getUserWinstreak(username);
        List<Double> avgAsMaker = controladorPersistencia.getUserAvgAsMaker(username);
        List<Double> avgAsBreaker = controladorPersistencia.getUserAvgAsBreaker(username);
        List<Integer> numGamesAsMaker = controladorPersistencia.getUserNumGamesAsMaker(username);
        user = new User(name, username);
        // user = new User(name, username, personalRecord, timePlayed, wonGames, lostGames, winStreak, avgAsBreaker, avgAsMaker, numGamesAsMaker);
        return true;
    }

    /**
     * Mètode per registrar un usuari no existent
     * @param username username del nou usuari
     * @param name nom del nou usuari
     * @param password contrasenya del nou usuari
     * @throws UserAlreadyExistsException si l'usuari ja existeix
     * @author Albert Canales
     */
    public void registerUser(String username, String name, String password) throws DomainException {
        if(existsUser(username))
            throw new UserAlreadyExistsException(username);
        controladorPersistencia.registerUser(username, name, password);
        user = new User(name, username);
    }

    /**
     * Mètode per tancar la sessió de l'usuari
     * @author Albert Canales
     */
    public void logoutUser() throws NotLoggedInException {
        if(user == null)
            throw new NotLoggedInException();
        user = null;
    }

    /**
     * Mètode per iniciar una nova partida amb el jugador com a maker
     * @param solucio solucio de la partida
     * @param algorisme enter que representa l'algorisme escollit per al breaker
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public void novaPartidaMaker(List<Integer> solucio, Integer algorisme) throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        controladorPartida.novaPartidaMaker(solucio, algorisme);
    }

    /**
     * Mètode per iniciar una nova partida amb el jugador com a breaker
     * @param nivellDificultat nivell de dificultat escollit pel jugador
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public void novaPartidaBreaker(Integer nivellDificultat) throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        controladorPartida.novaPartidaBreaker(nivellDificultat);
    }

    /**
     * Mètode per comprovar si existeix una partida guardada i és de l'usuari registrat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Booleà indicant si existeix una partida guardada
     * @author Albert Canales
     */
    public Boolean existsPartidaGuardada() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        if(controladorPersistencia.existsPartidaGuardada()) {
            String usernameGuardada = controladorPersistencia.getUserPartidaGuardada();
            return Objects.equals(usernameGuardada, user.getUsername());
        }
        return false;
    }

    /**
     * Mètode per carregar la partida guardada
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @throws NoGameSavedException si no hi ha una partida guardada d'aquest usuari
     * @author Albert Canales
     */
    public void carregarPartida() throws DomainException {
        if(!existsPartidaGuardada())
            throw new NoGameSavedException();
        Integer nivellDificultat = controladorPersistencia.getNivellDificultatPartidaGuardada();
        List<List<Integer>> intents = controladorPersistencia.getIntentsPartidaGuardada();
        List<List<Integer>> feedback = controladorPersistencia.getFeedbackPartidaGuardada();
        List<Integer> solucio = controladorPersistencia.getSolucioPartidaGuardada();
        if(controladorPersistencia.isBreakerPartidaGuardada())
            this.carregarPartidaBreaker(nivellDificultat, intents, feedback, solucio);
        else
            this.carregarPartidaMaker(nivellDificultat, intents, feedback, solucio);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és breaker
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    private void carregarPartidaBreaker(Integer nivellDificultat, List<List<Integer>> intents,
                                        List<List<Integer>> feedback, List<Integer> solucio) throws DomainException {
        Duration temps = controladorPersistencia.getTempsPartidaGuardada();
        controladorPartida.carregarPartidaBreaker(nivellDificultat, intents, feedback, solucio, temps);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és maker
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    private void carregarPartidaMaker(Integer nivellDificultat, List<List<Integer>> intents,
                                      List<List<Integer>> feedback, List<Integer> solucio) throws DomainException {
        controladorPartida.carregarPartidaMaker(nivellDificultat, intents, feedback, solucio);
    }

    /**
     * Getter del rànquing de les millors partides d'una dificultat concreta
     * @param nivellDificultat nombre del nivell de dificultat
     * @param nombrePartides nombre de partides a mostrar
     * @return Una llista que conté tuples amb (username: String, intents: Integer, temps: Duration)
     * @author Albert Canales
     */
    public List<List<Object>> getRanquing(Integer nivellDificultat, Integer nombrePartides) {
        return controladorPersistencia.getRanquing(nivellDificultat, nombrePartides);
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public List<Integer> getPersonalRecord() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return user.getPersonalRecord();
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public List<Integer> getTimePlayed() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return null;
        // return user.getTimePlayed();
    }

    /**
     * Getter de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWonGames() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return user.getWonGames();
    }

    /**
     * Getter de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<List<Integer>> getLostGames() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return null;
        // return user.getLostGames();
    }

    /**
     * Getter de ratxa de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWinstreak() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return user.getWinStreak();
    }

    /**
     * Getter de mitjana d'intents com a breaker
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb la mitjana d'intents per a cada dificultat
     * @author Albert Canales
     */
    public List<Double> getAverageAsBreaker() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return user.getAvgAsBreaker();
    }

    /**
     * Getter de mitjana d'intents com a maker
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada algorisme
     * @author Albert Canales
     */
    public List<Double> getAverageAsMaker() throws DomainException {
        if(user == null)
            throw new NotLoggedInException();
        return user.getAvgAsMaker();
    }

    /**
     * Getter de la solució de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return La seqüència solució de la partida actual
     * @author Albert Canales
     */
    public List<Integer> getSolucio() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        return controladorPartida.getSequenciaSolucio();
    }

    /**
     * Getter de tots els intents de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return Una llista amb tots els intents de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getIntents() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        return controladorPartida.getIntents();
    }

    /**
     * Getter de tots els feedbacks de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return Una llista amb tots els feedbacks de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getFeedbacks() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        return controladorPartida.getFeedbacks();
    }

    /**
     * Getter dels mil·lisegons transcorreguts en la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Long getTempsPartidaMillis() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        return controladorPartida.getTempsMillis();
    }

    /**
     * Mètode per afegir temps transcorregut a la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @param millis mi·lisegons a afegir
     * @author Albert Canales
     */
    public void addTempsPartidaMillis(Long millis) throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        controladorPartida.addTempsMillis(millis);
    }

    /**
     * Mètode per a col·locar una bola en l'intent actual de la partida actual
     * @param index La posició on es vol col·locar la bola
     * @param bola La bola que es vol col·locar
     * @throws DomainException si bola no és una Bola vàlida
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public void setBola(Integer index, Integer bola) throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        controladorPartida.setBola(index, bola);
    }

    // TODO Aquest mètode s'ha de revisar
    /**
     * Mètode per a validar l'intent actual en la partida actual
     * @return El feedback corresponent a l'intent donat
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public List<Integer> validarSequencia() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        List<Integer> feedback = controladorPartida.validarSequencia();

        if(controladorPartida.isPartidaAcabada()) {
            controladorPersistencia.acabarPartidaGuardada();

            Integer numIntents = controladorPartida.getNumIntents();
            if(controladorPartida.isJugadorBreaker()) {
                Integer nivellDificultat = controladorPartida.getNivellDificultat();
                Boolean guanyada = controladorPartida.isPartidaGuanyada();
                Long temps = controladorPartida.getTempsMillis();
                // user.acabarPartidaBreaker(nivellDificultat, numIntents, guanyada, temps);
            }
            else {
                Integer algorisme = controladorPartida.getAlgorisme();
                user.acabarPartidaMaker(algorisme, numIntents);
            }

            controladorPartida.sortirPartida();
        }
        return feedback;
    }

    /**
     * Mètode perquè el bot jugui la partida
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    void botSolve() throws DomainException {
        if(!controladorPartida.isPartidaPresent())
            throw new NotPlayingPartidaException();
        controladorPartida.botSolve();
    }
}
