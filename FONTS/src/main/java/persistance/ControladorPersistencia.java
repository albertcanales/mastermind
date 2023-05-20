package persistance;

import exceptions.GeneralException;
import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la capa de Persistència.
 * Segueix el patró de Singleton
 * @author Arnau Valls Fusté
 */
public class ControladorPersistencia {

    /**
     * Instància de la classe per la implementació del patró Singleton
     */
    private static ControladorPersistencia instance = null;

    /**
     * Instància de GestorUsuaris
     */
    private final GestorUsuaris gestorUsuaris;

    /**
     * Instància de GestorPartidesActuals
     */
    private final GestorPartidesActuals gestorPartidesActuals;

    /**
     * Instància de GestorRanquing
     */
    private final GestorRanquing gestorRanquing;

    /**
     * String que representa el directori on es guardaran tots els fitxers de persistència
     */
    private static final String basePath = "./db/";

    /**
     * Contructor de ControladorPersistencia
     * @throws PersistanceException si no es pot crear algun fitxer
     */
    private ControladorPersistencia() throws PersistanceException {
        gestorUsuaris = new GestorUsuaris(basePath);
        gestorPartidesActuals = new GestorPartidesActuals(basePath);
        gestorRanquing = new GestorRanquing(basePath);
    }

    /**
     * Mètode per obtenir l'única instància del controlador de persistència
     * @return L'única instància de ControladorPersistència
     * @throws PersistanceException Si no es pot iniciar la base de dades
     */
    public static ControladorPersistencia getInstance() throws GeneralException {
        if(instance == null)
            instance = new ControladorPersistencia();
        return instance;
    }

    /**
     * Comprova si l'usuari existeix a persistència
     * @param username el nom d'usuari
     * @return cert o fals depenenent de si l'usuari existeix a persistència
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public Boolean existsUser(String username) throws PersistanceException {
        return gestorUsuaris.existsUser(username);
    }

    /**
     * Registra un usuari a Persistència
     * @param username el nom d'usuari
     * @param name el nom complet
     * @param password la contrasenya
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal o si la línia ja existeix
     */
    public void registerUser(String username, String name, String password) throws PersistanceException {
        gestorUsuaris.registerUser(username, name, password);
    }

    /**
     * Retorna la contrasenya d'un Usuari registrat
     * @param username el nom d'usuari
     * @return la contrasenya
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public String getPasswordHash(String username) throws PersistanceException {
        return gestorUsuaris.getPasswordHash(username);
    }

    /**
     * Retorna el nom complet d'un Usuari registrat
     * @param username el nom d'usuari
     * @return el nom complet
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public String getUserName(String username) throws PersistanceException {
        return gestorUsuaris.getUserName(username);
    }

    /**
     * Retorna el rànquing guardat
     * @return el rànquing en una composició de llistes d'Strings
     * @throws PersistanceException si no es pot llegir algun fitxer
     */
    public List<List<List<String>>> getRanquings() throws PersistanceException {
        List<List<List<String>>> ranquing = new ArrayList<>();
        for (int i = 0; i < GestorRanquing.numFitxers; ++i){
            ranquing.add(gestorRanquing.getRanquing(i));
        }

        return ranquing;
    }

    /**
     * Estableix el rànquing guardat
     * @param ranquing el rànquing en una composició de llistes d'Strings
     * @throws PersistanceException si no es pot escriure algun fitxer
     */
    public void setRanquings(List<List<List<String>>> ranquing) throws PersistanceException {
        for (int i = 0; i < GestorRanquing.numFitxers; ++i){
            gestorRanquing.setRanquing(i, ranquing.get(i));
        }
    }

    /**
     * Retorna el PersonalRecord d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del PersonalRecord
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserPersonalRecord(String username) throws PersistanceException {
        return gestorUsuaris.getPersonalRecord(username);
    }

    /**
     * Retorna el TimePlayed d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del TimePlayed
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Long> getUserTimePlayed(String username) throws PersistanceException {
        return gestorUsuaris.getTimePlayed(username);
    }

    /**
     * Retorna els WonGames d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista dels WonGames
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserWonGames(String username) throws PersistanceException {
        return gestorUsuaris.getWonGames(username);
    }

    /**
     * Retorna els LostGames d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista dels LostGames
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserLostGames(String username) throws PersistanceException {
        return gestorUsuaris.getLostGames(username);
    }

    /**
     * Retorna el CurrentWinstreak d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del CurrentWinstreak
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserCurrentWinstreak(String username) throws PersistanceException {
        return gestorUsuaris.getCurrentWinstreak(username);
    }

    /**
     * Retorna el Winstreak d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del Winstreak
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserWinstreak(String username) throws PersistanceException {
        return gestorUsuaris.getWinstreak(username);
    }

    /**
     * Retorna el AvgAsMaker d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del AvgAsMaker
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Double> getUserAvgAsMaker(String username) throws PersistanceException {
        return gestorUsuaris.getAvgAsMaker(username);
    }

    /**
     * Retorna el AvgAsBreaker d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del AvgAsBreaker
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Double> getUserAvgAsBreaker(String username) throws PersistanceException {
        return gestorUsuaris.getAvgAsBreaker(username);
    }

    /**
     * Retorna el NumGamesAsMaker d'un Usuari registrat
     * @param username el nom d'usuari
     * @return una llista del NumGamesAsMaker
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getUserNumGamesAsMaker(String username) throws PersistanceException {
        return gestorUsuaris.getNumGamesAsMaker(username);
    }

    /**
     * Estableix els Stats d'un Usuari registrat
     * @param username el nom d'usuari
     * @param pr una llista del PersonalRecord
     * @param time una llista del TimePlayed
     * @param won una llista dels WonGames
     * @param lost una llista dels LostGames
     * @param currentWs una llista del CurrentWinstreak
     * @param ws una llista del Winstreak
     * @param avgMaker una llista del AvgAsMaker
     * @param avgBreaker una llista del AvgAsBreaker
     * @param gamesMaker una llista del NumGamesAsMaker
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setUserStats(String username, List<Integer> pr, List<Long> time, List<Integer> won, List<Integer> lost,
                             List<Integer> currentWs, List<Integer> ws, List<Double> avgMaker, List<Double> avgBreaker,
                             List<Integer> gamesMaker) throws PersistanceException {
        gestorUsuaris.setStats(username, pr, time, won, lost, currentWs, ws, avgMaker, avgBreaker, gamesMaker);
    }

    /**
     * Retorna el NivellDificultat d'una partida Guardada
     * @param username el nom d'usuari
     * @return el NivellDificultat
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public Integer getNivellDificultatPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getDificultat(username);
    }

    /**
     * Retorna l'Algorisme d'una partida Guardada
     * @param username el nom d'usuari
     * @return l'Algorisme
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public Integer getAlgorismePartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getAlgorisme(username);
    }

    /**
     * Retorna els intents d'una partida Guardada
     * @param username el nom d'usuari
     * @return una llista dels intents
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<List<Integer>> getIntentsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getIntents(username);
    }

    /**
     * Estableix els intents d'una partida Guardada
     * @param username el nom d'usuari
     * @param intents una llista dels intents
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setIntentsPartidaGuardada(String username, List<List<Integer>> intents) throws PersistanceException {
        gestorPartidesActuals.setIntents(username, intents);
    }

    /**
     * Retorna els feedbacks d'una partida Guardada
     * @param username el nom d'usuari
     * @return una llista dels feedbacks
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<List<Integer>> getFeedbackPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getFeedbacks(username);
    }

    /**
     * Estableix els feedbacks d'una partida Guardada
     * @param username el nom d'usuari
     * @param feedbacks una llista dels feedbacks
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setFeedbacksPartidaGuardada(String username, List<List<Integer>> feedbacks) throws PersistanceException {
        gestorPartidesActuals.setFeedbacks(username, feedbacks);
    }

    /**
     * Retorna la solució d'una partida Guardada
     * @param username el nom d'usuari
     * @return una llista de la solució
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public List<Integer> getSolucioPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getSolucio(username);
    }

    /**
     * Comprova si existeix una partida de l'usuari a persistència
     * @param username el nom d'usuari
     * @return cert o fals depenenent de si existeix una partida de l'usuari a persistència
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public Boolean existsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.existsPartidaActual(username);
    }

    /**
     * Retorna l'atribut isBreaker d'una partida existent
     * @param username el nom d'usuari
     * @return el booleà isBreaker
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public Boolean partidaGuardadaisBreaker(String username) throws PersistanceException {
        return gestorPartidesActuals.isBreaker(username);
    }

    /**
     * Retorna l'atribut Temps d'una partida existent
     * @param username el nom d'usuari
     * @return el Long Temps
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public Long getTempsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getTemps(username);
    }

    /**
     * Estableix l'atribut Temps d'una partida existent
     * @param username el nom d'usuari
     * @param temps el Long Temps
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setTempsPartidaGuardada(String username, Long temps) throws PersistanceException {
        gestorPartidesActuals.setTemps(username, temps);
    }

    /**
     * Retorna l'atribut SolucioVista d'una partida existent
     * @param username el nom d'usuari
     * @return el booleà SolucioVista
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public Boolean getSolucioVistaPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getSolucioVista(username);
    }

    /**
     * Estableix l'atribut SolucioVista d'una partida existent
     * @param username el nom d'usuari
     * @param solucioVista el booleà SolucioVista
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setSolucioVistaPartidaGuardada(String username, Boolean solucioVista) throws PersistanceException {
        gestorPartidesActuals.setSolucioVista(username, solucioVista);
    }

    /**
     * Esborra un usuari registrat a persistència
     * @param username el nom d'usuari
     * @throws GeneralException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void esborrarUsuari(String username) throws GeneralException {
        gestorUsuaris.esborrarUsuari(username);
    }

    /**
     * Esborra una partida d'un usuari registrat a persistència
     * @param username el nom d'usuari
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void esborrarPartida(String username) throws PersistanceException {
        gestorPartidesActuals.esborrarPartidaActual(username);
    }

    /**
     * Crea una nova partida "Maker" de l'usuari registrat a persistència
     * @param username el nom d'usuari
     * @param solucio una llista de la solució
     * @param algorisme un enter de l'algorisme
     * @param intents una llista dels intents
     * @param feedback una llista dels feedbacks
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal o si la línia ja existeix
     */
    public void novaPartidaMaker(String username, List<Integer> solucio, Integer algorisme, List<List<Integer>> intents,
                                 List<List<Integer>> feedback) throws PersistanceException {
        gestorPartidesActuals.novaPartida(username, false, null, null, algorisme, solucio, intents, feedback);
    }

    /**
     * Crea una nova partida "Breaker" de l'usuari registrat a persistència
     * @param username el nom d'usuari
     * @param nivellDificultat un enter de la dificultat
     * @param temps un Long del temps
     * @param solucio una llista de la solució
     * @param intents una llista dels intents
     * @param feedback una llista dels feedbacks
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal o si la línia ja existeix
     */
    public void novaPartidaBreaker(String username, Integer nivellDificultat, Long temps, List<Integer> solucio, List<List<Integer>> intents,
                                   List<List<Integer>> feedback) throws PersistanceException {
        gestorPartidesActuals.novaPartida(username, true, temps, nivellDificultat, null, solucio, intents, feedback);
    }
}
