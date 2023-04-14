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
     * Mètode per saber si s'ha iniciat sessió
     * @author Albert Canales
     */
    public Boolean userLoggedIn() {
        return user != null;
    }

    /**
     * Mètode per saber s'està jugant una partida
     * @author Albert Canales
     */
    public Boolean isPartidaBeingPlayed() {
        return controladorPartida.isPartidaPresent();
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
     * Mètode per saber si els paràmetres d'un usuari són vàlids
     * @param username contrasenya a comprovar
     * @param name nom a comprovar
     * @param password contrasenya a comprovar
     * @author Albert Canales
     */
    public Boolean isValidUser(String username, String name, String password) {
        return User.isValidUser(username, name, password);
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
        List<Long> timePlayed = controladorPersistencia.getUserTimePlayed(username);
        List<Integer> wonGames = controladorPersistencia.getUserWonGames(username);
        List<Integer> lostGames = controladorPersistencia.getUserLostGames(username);
        List<Integer> currentWinStreak = controladorPersistencia.getUserCurrentWinstreak(username);
        List<Integer> winStreak = controladorPersistencia.getUserWinstreak(username);
        List<Double> avgAsMaker = controladorPersistencia.getUserAvgAsMaker(username);
        List<Double> avgAsBreaker = controladorPersistencia.getUserAvgAsBreaker(username);
        List<Integer> numGamesAsMaker = controladorPersistencia.getUserNumGamesAsMaker(username);
        user = new User(name, username);
        user = new User(name, username, personalRecord, timePlayed, wonGames, lostGames, currentWinStreak, winStreak, avgAsBreaker, avgAsMaker, numGamesAsMaker);
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
        if(!isValidUser(username, name, password))
            throw new InvalidUserException();
        controladorPersistencia.registerUser(username, name, password);
        user = new User(name, username);
    }

    /**
     * Mètode per tancar la sessió de l'usuari
     * @author Albert Canales
     */
    public void logoutUser() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        if(isPartidaBeingPlayed())
            sortirPartida();
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
        if(!userLoggedIn())
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
        if(!userLoggedIn())
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
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return controladorPersistencia.existsPartidaGuardada(user.getUsername());
    }

    /**
     * Mètode per carregar la partida guardada
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @throws NoGameSavedException si no hi ha una partida guardada d'aquest usuari
     * @author Albert Canales
     */
    public void carregarPartida() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        if(!existsPartidaGuardada())
            throw new NoGameSavedException();
        String username = user.getUsername();
        List<List<Integer>> intents = controladorPersistencia.getIntentsPartidaGuardada(username);
        List<List<Integer>> feedback = controladorPersistencia.getFeedbackPartidaGuardada(username);
        List<Integer> solucio = controladorPersistencia.getSolucioPartidaGuardada(username);
        if(controladorPersistencia.isBreakerPartidaGuardada(username)) {
            this.carregarPartidaBreaker(username, intents, feedback, solucio);
        }
        else
            this.carregarPartidaMaker(username, intents, feedback, solucio);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és breaker
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    private void carregarPartidaBreaker(String username, List<List<Integer>> intents,
                                        List<List<Integer>> feedback, List<Integer> solucio) throws DomainException {
        Integer nivellDificultat = controladorPersistencia.getNivellDificultatPartidaGuardada(username);
        Long temps = controladorPersistencia.getTempsPartidaGuardada(username);
        controladorPartida.carregarPartidaBreaker(nivellDificultat, intents, feedback, solucio, temps);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és maker
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @author Albert Canales
     */
    private void carregarPartidaMaker(String username, List<List<Integer>> intents,
                                      List<List<Integer>> feedback, List<Integer> solucio) throws DomainException {
        Integer algorisme = controladorPersistencia.getAlgorismePartidaGuardada(username);
        controladorPartida.carregarPartidaMaker(algorisme, intents, feedback, solucio);
    }

    /**
     * Getter del rànquing de les millors partides d'una dificultat concreta
     * @param nivellDificultat nombre del nivell de dificultat
     * @return Una llista que conté tuples amb (username: String, intents: Integer, temps: Long)
     * @author Albert Canales
     */
    public List<List<Object>> getRanquing(Integer nivellDificultat) throws InvalidEnumValueException {
        if(!NivellDificultat.isValid(nivellDificultat))
            throw new InvalidEnumValueException("NivellDificultat", nivellDificultat.toString());
        return controladorPersistencia.getRanquing(nivellDificultat);
    }

    /**
     * Getter del nom de l'usuari que ha iniciat sessió
     * @throws DomainException no s'ha iniciat sessió
     * @author Albert Canales
     */
    public String getUserName() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getName();
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public List<Integer> getPersonalRecord() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getPersonalRecord();
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @author Albert Canales
     */
    public List<Long> getTimePlayed() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getTimePlayed();
    }

    /**
     * Getter de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWonGames() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getWonGames();
    }

    /**
     * Getter de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getLostGames() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getLostGames();
    }

    /**
     * Getter de ratxa de victòries
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWinstreak() throws DomainException {
        if(!userLoggedIn())
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
        if(!userLoggedIn())
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
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getAvgAsMaker();
    }

    /**
     * Mètode per saber si el jugador fa de breaker en la partida actual
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Boolean isJugadorBreaker() throws DomainException {
        return controladorPartida.isJugadorBreaker();
    }

    /**
     * Getter de la solució de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return La seqüència solució de la partida actual
     * @author Albert Canales
     */
    public List<Integer> getSolucio() throws DomainException {
        return controladorPartida.getSequenciaSolucio();
    }

    /**
     * Getter de tots els intents de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return Una llista amb tots els intents de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getIntents() throws DomainException {
        return controladorPartida.getIntents();
    }

    /**
     * Getter de tots els feedbacks de la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @return Una llista amb tots els feedbacks de la partida actual
     * @author Albert Canales
     */
    public List<List<Integer>> getFeedbacks() throws DomainException {
        return controladorPartida.getFeedbacks();
    }

    /**
     * Getter dels mil·lisegons transcorreguts en la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Long getTempsPartidaMillis() throws DomainException {
        return controladorPartida.getTempsMillis();
    }

    /**
     * Mètode per afegir temps transcorregut a la partida actual
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @param millis mi·lisegons a afegir
     * @author Albert Canales
     */
    public void addTempsPartidaMillis(Long millis) throws DomainException {
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
        controladorPartida.setBola(index, bola);
    }

    /**
     * Mètode per a validar l'intent actual en la partida actual
     * @return El feedback corresponent a l'intent donat
     * @throws DomainException si el tamany d'alguna list no és correcte
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public List<Integer> validarSequencia() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return controladorPartida.validarSequencia();
    }

    /**
     * Mètode perquè el bot jugui la partida
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public void botSolve() throws DomainException {
        controladorPartida.botSolve();
    }

    /**
     * Mètode per saber si una partida està guanyada
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Boolean isPartidaGuanyada() throws DomainException {
        return controladorPartida.isPartidaGuanyada();
    }

    /**
     * Mètode per saber si una partida està perduda
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Boolean isPartidaPerduda() throws DomainException {
        return controladorPartida.isPartidaPerduda();
    }

    /**
     * Mètode per saber si una partida està acabada
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Boolean isPartidaAcabada() throws DomainException {
        return controladorPartida.isPartidaAcabada();
    }

    /**
     * Mètode per saber si l'últim intent està ple
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public Boolean isUltimIntentPle() throws DomainException {
        return controladorPartida.isUltimIntentPle();
    }

    /**
     * Mètode per sortir de la partida
     * Si està acabada, l'esborrarà de la partida carregada i actualitzarà les estadístiques
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public void sortirPartida() throws DomainException {
        if(!isPartidaBeingPlayed())
            throw new NotPlayingPartidaException();
        if(controladorPartida.isPartidaAcabada()) {
            // Guarda la partida com a finalitzada
            controladorPersistencia.acabarPartidaGuardada(user.getUsername());

            // Actualitza directament els valors de user per ser més eficient
            Integer numIntents = controladorPartida.getNumIntents();
            if(controladorPartida.isJugadorBreaker()) {
                Integer nivellDificultat = controladorPartida.getNivellDificultat();
                Boolean guanyada = controladorPartida.isPartidaGuanyada();
                Long temps = controladorPartida.getTempsMillis();
                user.acabarPartidaBreaker(nivellDificultat, numIntents, guanyada, temps);
            }
            else {
                Integer algorisme = controladorPartida.getAlgorisme();
                user.acabarPartidaMaker(algorisme, numIntents);
            }
        }
        controladorPartida.sortirPartida();
    }

    /**
     * Mètode per esborrar l'usuari que ha iniciat sessió
     * També tanca la sessió automàticament
     * @throws DomainException si no s'està jugant cap partida
     * @author Albert Canales
     */
    public void esborrarUsuari() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        String username = user.getUsername();
        logoutUser();
        controladorPersistencia.esborrarUsuari(username);
    }
}
