package domain;

import exceptions.GeneralException;
import exceptions.domain.*;
import persistance.ControladorPersistencia;

import java.util.List;
import java.util.Objects;

/**
 * Controlador de la capa de domini.
 * Segueix el patró de Singleton
 * @author Albert Canales
 */
public class ControladorDomini {

    /**
     * Instància de la classe per la implementació del patró Singleton
     */
    private static ControladorDomini instance = null;

    /**
     * Instància del controlador de partida
     */
    private final ControladorPartida controladorPartida;

    /**
     * Instància del controlador de persistència
     */
    private final ControladorPersistencia controladorPersistencia;

    /**
     * Usuari que ha iniciat sessió
     */
    private User user;

    /**
     * Rànquing de les partides acabades
     */
    private Ranquing ranquing;

    /**
     * Constructor del Controlador de Domini
     * @throws GeneralException si no es pot iniciar la base de dades
     */
    private ControladorDomini() throws GeneralException {
        controladorPartida = ControladorPartida.getInstance();
        controladorPersistencia = ControladorPersistencia.getInstance();
        user = null;
        ranquing = null;
    }

    /**
     * Mètode per obtenir l'única instància del controlador de domini
     * @return L'única instància de ControladorDomini
     * @throws GeneralException Si no es pot iniciar la base de dades
     */
    public static ControladorDomini getInstance() throws GeneralException {
        if(instance == null)
            instance = new ControladorDomini();
        return instance;
    }

    /**
     * Mètode per saber si s'ha iniciat sessió
     * @return Si l'usuari ha iniciat sessió
     */
    public Boolean userLoggedIn() {
        return user != null;
    }

    /**
     * Mètode per saber s'està jugant una partida
     * @return Si s'està jugant una partida
     */
    public Boolean isPartidaBeingPlayed() {
        return controladorPartida.isPartidaPresent();
    }



    /**
     * Mètode per comprovar si existeix un usuari
     * @param username Username de l'usuari a comprovar
     * @return Si l'usuari donat existeix
     * @throws GeneralException Si hi ha cap problema per accedir a persistència
     */
    public Boolean existsUser(String username) throws GeneralException {
        return controladorPersistencia.existsUser(username);
    }

    /**
     * Mètode per saber si els paràmetres d'un usuari són vàlids
     * @param username Username a comprovar
     * @return si el nom d'usuari donat és vàlid
     */
    public Boolean isValidUsername(String username) {
        return User.isValidUsername(username);
    }

    /**
     * Mètode per saber si el nom complet d'un usuari és vàlid
     * @param name Nom a comprovar
     * @return si el nom donat és vàlid
     */
    public Boolean isValidName(String name) {
        return User.isValidName(name);
    }

    /**
     * Mètode per saber si els paràmetres d'un usuari són vàlids
     * @param password Contrasenya a comprovar
     * @return si la contrasenya és vàlida
     */
    public Boolean isValidPassword(String password) {
        return User.isValidPassword(password);
    }

    /**
     * Mètode per saber si els paràmetres d'un usuari són vàlids
     * @param username Username a comprovar
     * @param name Nom a comprovar
     * @param password Contrasenya a comprovar
     * @return si l'usuari donat és vàlid
     */
    public Boolean isValidUser(String username, String name, String password) {
        return User.isValidUser(username, name, password);
    }

    /**
     * Mètode per iniciar sessió d'un usuari existent
     * @param username username del l'usuari
     * @param password contrasenya de l'usuari
     * @return si la contrasenya donada és correcta
     * @throws UserNotExistsException si l'usuari no existeix
     */
    public Boolean loginUser(String username, String password) throws GeneralException {
        if(!existsUser(username))
            throw new UserNotExistsException(username);
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
        user = new User(name, username, personalRecord, timePlayed, wonGames, lostGames, currentWinStreak, winStreak, avgAsBreaker, avgAsMaker, numGamesAsMaker);
        return true;
    }

    /**
     * Mètode per registrar un usuari no existent
     * @param username username del nou usuari
     * @param name nom del nou usuari
     * @param password contrasenya del nou usuari
     * @throws UserAlreadyExistsException si l'usuari ja existeix
     */
    public void registerUser(String username, String name, String password) throws GeneralException {
        if(existsUser(username))
            throw new UserAlreadyExistsException(username);
        if(!isValidUser(username, name, password))
            throw new InvalidUserException();
        controladorPersistencia.registerUser(username, name, User.getPasswordHash(password));
        user = new User(name, username);
        controladorPersistencia.setUserStats(username, user.getPersonalRecord(), user.getTimePlayed(), user.getWonGames(), user.getLostGames(),
                user.getCurrentWinStreak(), user.getWinStreak(), user.getAvgAsMaker(), user.getAvgAsBreaker(), user.getNumGamesAsMaker());
    }

    /**
     * Mètode per tancar la sessió de l'usuari
     * @throws DomainException Si no hi havia cap usuari que havia iniciat sessió
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
     */
    public void novaPartidaMaker(List<Integer> solucio, Integer algorisme) throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        if (controladorPersistencia.existsPartidaGuardada(user.getUsername()))
            controladorPersistencia.esborrarPartida(user.getUsername());
        controladorPartida.novaPartidaMaker(solucio, algorisme);
        List<List<Integer>> intents = controladorPartida.getIntents();
        List<List<Integer>> feedback = controladorPartida.getFeedbacks();
        controladorPersistencia.novaPartidaMaker(user.getUsername(), solucio, algorisme, intents, feedback);
    }

    /**
     * Mètode per iniciar una nova partida amb el jugador com a breaker
     * @param nivellDificultat nivell de dificultat escollit pel jugador
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public void novaPartidaBreaker(Integer nivellDificultat) throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        if (controladorPersistencia.existsPartidaGuardada(user.getUsername()))
            controladorPersistencia.esborrarPartida(user.getUsername());
        controladorPartida.novaPartidaBreaker(nivellDificultat);
        List<Integer> solucio = controladorPartida.getSequenciaSolucio();
        List<List<Integer>> intents = controladorPartida.getIntents();
        List<List<Integer>> feedback = controladorPartida.getFeedbacks();
        Long temps = controladorPartida.getTempsMillis();
        controladorPersistencia.novaPartidaBreaker(user.getUsername(), nivellDificultat, temps, solucio, intents, feedback);
        controladorPersistencia.setSolucioVistaPartidaGuardada(user.getUsername(), false);
    }

    /**
     * Mètode per comprovar si existeix una partida guardada i és de l'usuari registrat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @return Booleà indicant si existeix una partida guardada
     */
    public Boolean existsPartidaGuardada() throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return controladorPersistencia.existsPartidaGuardada(user.getUsername());
    }

    /**
     * Mètode per carregar la partida guardada
     * @throws NotLoggedInException si no s'ha iniciat sessió
     * @throws NoGameSavedException si no hi ha una partida guardada d'aquest usuari
     */
    public void carregarPartida() throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        if(!existsPartidaGuardada())
            throw new NoGameSavedException();
        String username = user.getUsername();
        List<List<Integer>> intents = controladorPersistencia.getIntentsPartidaGuardada(username);
        List<List<Integer>> feedbacks = controladorPersistencia.getFeedbackPartidaGuardada(username);
        List<Integer> solucio = controladorPersistencia.getSolucioPartidaGuardada(username);
        if(controladorPersistencia.partidaGuardadaisBreaker(username)) {
            this.carregarPartidaBreaker(username, intents, feedbacks, solucio);
        }
        else
            this.carregarPartidaMaker(username, intents, feedbacks, solucio);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és breaker
     * @throws DomainException si la mida d'alguna list no és correcte
     */
    private void carregarPartidaBreaker(String username, List<List<Integer>> intents,
                                        List<List<Integer>> feedbacks, List<Integer> solucio) throws GeneralException {
        Integer nivellDificultat = controladorPersistencia.getNivellDificultatPartidaGuardada(username);
        Long temps = controladorPersistencia.getTempsPartidaGuardada(username);
        Boolean solucioVista = controladorPersistencia.getSolucioVistaPartidaGuardada(username);
        controladorPartida.carregarPartidaBreaker(nivellDificultat, intents, feedbacks, solucio, temps, solucioVista);
    }

    /**
     * Mètode per carregar la partida guardada on el jugador és maker
     * @throws DomainException si la mida d'alguna list no és correcte
     */
    private void carregarPartidaMaker(String username, List<List<Integer>> intents,
                                      List<List<Integer>> feedbacks, List<Integer> solucio) throws GeneralException {
        Integer algorisme = controladorPersistencia.getAlgorismePartidaGuardada(username);
        controladorPartida.carregarPartidaMaker(algorisme, intents, feedbacks, solucio);
    }

    /**
     * Mètode per obtenir els rànquings. Hi ha un rànquing per dificultat, cada un amb entrades de tres valors
     * que són {username, intents, time}
     * @param max_rows Nombre màxim de files que es volen obtenir
     * @return Tots els rànquings
     * @throws GeneralException Si persistència no pot proporcionar els rànquings
     */
    public List<List<List<String>>> getRanquings(Integer max_rows) throws GeneralException {
        if(ranquing == null)
            ranquing = new Ranquing(controladorPersistencia.getRanquings());
        return ranquing.getRanquings(max_rows);
    }

    /**
     * Getter del nom de l'usuari que ha iniciat sessió
     * @return El nom de l'usuari que ha iniciat sessió
     * @throws DomainException no s'ha iniciat sessió
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
     */
    public List<Integer> getPersonalRecord() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getPersonalRecord();
    }

    /**
     * Getter del temps jugat com a breaker de l'usuari actual
     * @return Una llista amb el temps jugat com a breaker per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Long> getTimePlayed() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getTimePlayed();
    }

    /**
     * Getter de victòries
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Integer> getWonGames() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getWonGames();
    }

    /**
     * Getter de victòries
     * @return Una llista amb el nombre de victòries per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Integer> getLostGames() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getLostGames();
    }

    /**
     * Getter de ratxa de victòries
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Integer> getWinstreak() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getWinStreak();
    }

    /**
     * Getter de mitjana d'intents com a breaker
     * @return Una llista amb la mitjana d'intents per a cada dificultat
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Double> getAverageAsBreaker() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getAvgAsBreaker();
    }

    /**
     * Getter de mitjana d'intents com a maker
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada algorisme
     * @throws NotLoggedInException si no s'ha iniciat sessió
     */
    public List<Double> getAverageAsMaker() throws DomainException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        return user.getAvgAsMaker();
    }

    /**
     * Mètode per saber si el jugador fa de breaker en la partida actual
     * @return Si el jugador de la partida actual és el breaker
     * @throws DomainException si no s'està jugant cap partida
     */
    public Boolean isJugadorBreaker() throws DomainException {
        return controladorPartida.isJugadorBreaker();
    }

    /**
     * Getter de la solució de la partida actual
     * @return La seqüència solució de la partida actual
     * @throws DomainException si no s'està jugant cap partida
     */
    public List<Integer> getSolucio() throws DomainException {
        return controladorPartida.getSequenciaSolucio();
    }

    /**
     * Getter de tots els intents de la partida actual
     * @return Una llista amb tots els intents de la partida actual
     * @throws DomainException si no s'està jugant cap partida
     */
    public List<List<Integer>> getIntents() throws DomainException {
        return controladorPartida.getIntents();
    }

    /**
     * Getter de tots els feedbacks de la partida actual
     * @throws DomainException si no s'està jugant cap partida
     * @return Una llista amb tots els feedbacks de la partida actual
     */
    public List<List<Integer>> getFeedbacks() throws DomainException {
        return controladorPartida.getFeedbacks();
    }

    /**
     * Getter dels mil·lisegons transcorreguts en la partida actual
     * @return Els mil·lisegons que han transcorregut en la partida actual
     * @throws DomainException si no s'està jugant cap partida
     */
    public Long getTempsPartidaMillis() throws DomainException {
        return controladorPartida.getTempsMillis();
    }

    /**
     * Mètode per afegir temps transcorregut a la partida actual
     * @param millis mi·lisegons a afegir
     * @throws GeneralException si no s'està jugant cap partida o no es pot assignar a persistència
     */
    public void addTempsPartidaMillis(Long millis) throws GeneralException {
        controladorPartida.addTempsMillis(millis);
        controladorPersistencia.setTempsPartidaGuardada(user.getUsername(), controladorPartida.getTempsMillis());
    }

    /**
     * Mètode per assignar la solució com a vista de la partida actual
     * També dona la partida com a perduda
     * @throws GeneralException Si no s'està jugant cap partida o no es poden actualitzar les estadístiques
     */
    public void veureSolucio() throws GeneralException {
        controladorPartida.veureSolucio();
        controladorPersistencia.setSolucioVistaPartidaGuardada(user.getUsername(), true);

        Integer numIntents = controladorPartida.getNumIntents();
        Integer nivellDificultat = controladorPartida.getNivellDificultat();
        Long temps = controladorPartida.getTempsMillis();
        user.acabarPartidaBreaker(nivellDificultat, numIntents, false, temps);
        controladorPersistencia.setUserStats(user.getUsername(), user.getPersonalRecord(), user.getTimePlayed(), user.getWonGames(), user.getLostGames(),
                user.getCurrentWinStreak(), user.getWinStreak(), user.getAvgAsMaker(), user.getAvgAsBreaker(), user.getNumGamesAsMaker());
    }

    /**
     * Mètode per a col·locar una bola en l'intent actual de la partida actual
     * @param index La posició on es vol col·locar la bola
     * @param bola La bola que es vol col·locar
     * @throws GeneralException si bola no és vàlida o no es pot assignar a persistència
     */
    public void setBola(Integer index, Integer bola) throws GeneralException {
        controladorPartida.setBola(index, bola);
        controladorPersistencia.setIntentsPartidaGuardada(user.getUsername(), controladorPartida.getIntents());
    }

    /**
     * Mètode per a validar l'intent actual en la partida actual
     * Si acaba la partida, també s'actualitzen les estadístiques i rànquings
     * @return El feedback corresponent a l'intent donat
     * @throws GeneralException si la mida d'alguna list no és correcte o no es pot fer la modificació a persistència
     */
    public List<Integer> validarSequencia() throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        List<Integer> feedback = controladorPartida.validarSequencia();

        if(controladorPartida.isPartidaAcabada()) {
            Integer numIntents = controladorPartida.getNumIntents();
            Integer nivellDificultat = controladorPartida.getNivellDificultat();
            Boolean guanyada = controladorPartida.isPartidaGuanyada();
            Long temps = controladorPartida.getTempsMillis();
            user.acabarPartidaBreaker(nivellDificultat, numIntents, guanyada, temps);
            controladorPersistencia.setUserStats(user.getUsername(), user.getPersonalRecord(), user.getTimePlayed(), user.getWonGames(), user.getLostGames(),
                    user.getCurrentWinStreak(), user.getWinStreak(), user.getAvgAsMaker(), user.getAvgAsBreaker(), user.getNumGamesAsMaker());
            if (guanyada){
                if(ranquing == null)
                    ranquing = new Ranquing(controladorPersistencia.getRanquings());
                ranquing.acabarPartidaBreaker(user.getUsername(), nivellDificultat, numIntents, temps);
                controladorPersistencia.setRanquings(ranquing.getRanquings());
            }
        }
        controladorPersistencia.setFeedbacksPartidaGuardada(user.getUsername(), controladorPartida.getFeedbacks());
        controladorPersistencia.setIntentsPartidaGuardada(user.getUsername(), controladorPartida.getIntents());
        return feedback;
    }

    /**
     * Mètode perquè el bot jugui la partida.
     * També actualitza les estadístiques de l'usuari corresponents
     * @throws NotPlayingPartidaException si no s'està jugant cap partida
     */
    public void botSolve() throws GeneralException {
        controladorPartida.botSolve();
        Integer algorisme = controladorPartida.getAlgorisme();
        Integer numIntents = controladorPartida.getNumIntents();
        user.acabarPartidaMaker(algorisme, numIntents);
        controladorPersistencia.setUserStats(user.getUsername(), user.getPersonalRecord(), user.getTimePlayed(), user.getWonGames(), user.getLostGames(),
                user.getCurrentWinStreak(), user.getWinStreak(), user.getAvgAsMaker(), user.getAvgAsBreaker(), user.getNumGamesAsMaker());
    }

    /**
     * Mètode per saber si una partida està guanyada
     * @throws DomainException si no s'està jugant cap partida
     * @return Si la partida actual està guanyada
     */
    public Boolean isPartidaGuanyada() throws DomainException {
        return controladorPartida.isPartidaGuanyada();
    }

    /**
     * Mètode per saber si una partida està acabada
     * @return Si la partida que s'està jugant està acabada
     * @throws DomainException si no s'està jugant cap partida
     */
    public Boolean isPartidaAcabada() throws DomainException {
        return controladorPartida.isPartidaAcabada();
    }

    /**
     * Mètode per saber si l'últim intent està ple
     * @return Si l'últim intent de la partida actual està ple
     * @throws DomainException si no s'està jugant cap partida
     */
    public Boolean isUltimIntentPle() throws DomainException {
        return controladorPartida.isUltimIntentPle();
    }

    /**
     * Mètode per esborrar la partida carregada
     * @throws DomainException si no s'està jugant cap partida
     */
    public void sortirPartida() throws DomainException {
        if(!isPartidaBeingPlayed())
            throw new NotPlayingPartidaException();
        controladorPartida.sortirPartida();
    }

    /**
     * Mètode per esborrar l'usuari que ha iniciat sessió
     * També tanca la sessió automàticament
     * @throws DomainException si no s'ha iniciat sessió
     */
    public void esborrarUsuari() throws GeneralException {
        if(!userLoggedIn())
            throw new NotLoggedInException();
        String username = user.getUsername();
        logoutUser();
        controladorPersistencia.esborrarUsuari(username);
        if (controladorPersistencia.existsPartidaGuardada(username))
            controladorPersistencia.esborrarPartida(username);
        if(ranquing == null)
            ranquing = new Ranquing(controladorPersistencia.getRanquings());
        ranquing.esborrarUserFromRanquings(username);
        controladorPersistencia.setRanquings(ranquing.getRanquings());
    }
}
