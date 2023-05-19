package persistance;

import exceptions.GeneralException;
import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersistencia {

    private final GestorUsuaris gestorUsuaris;
    private final GestorPartidesActuals gestorPartidesActuals;
    private final GestorRanquing gestorRanquing;
    private static final String basePath = "./db/"; //TODO: directoris dinàmics
    public ControladorPersistencia() throws PersistanceException {
        gestorUsuaris = new GestorUsuaris(basePath);
        gestorPartidesActuals = new GestorPartidesActuals(basePath);
        gestorRanquing = new GestorRanquing(basePath);
    }
    public Boolean existsUser(String username) throws PersistanceException {
        return gestorUsuaris.existsUser(username);
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        gestorUsuaris.registerUser(username, name, password);
    }

    public String getPasswordHash(String username) throws PersistanceException {
        return gestorUsuaris.getPasswordHash(username);
    }

    public String getUserName(String username) throws PersistanceException {
        return gestorUsuaris.getUserName(username);
    }

    public List<List<List<String>>> getRanquing() throws PersistanceException {
        List<List<List<String>>> ranquing = new ArrayList<>();
        for (int i = 0; i < GestorRanquing.numFitxers; ++i){
            ranquing.add(gestorRanquing.getRanquing(i));
        }

        return ranquing;
    }
    public List<List<Object>> getRanquing(Integer nivellDificultat) throws PersistanceException {
        return new ArrayList<>();
    }

    public void setRanquing(List<List<List<String>>> ranquing) throws PersistanceException {
        for (int i = 0; i < GestorRanquing.numFitxers; ++i){
            gestorRanquing.setRanquing(i, ranquing.get(i));
        }
    }

    public List<Integer> getUserPersonalRecord(String username) throws PersistanceException {
        return gestorUsuaris.getPersonalRecord(username);
    }

    public List<Long> getUserTimePlayed(String username) throws PersistanceException {
        return gestorUsuaris.getTimePlayed(username);
    }

    public List<Integer> getUserWonGames(String username) throws PersistanceException {
        return gestorUsuaris.getWonGames(username);
    }

    public List<Integer> getUserLostGames(String username) throws PersistanceException {
        return gestorUsuaris.getLostGames(username);
    }

    public List<Integer> getUserCurrentWinstreak(String username) throws PersistanceException {
        return gestorUsuaris.getCurrentWinstreak(username);
    }

    public List<Integer> getUserWinstreak(String username) throws PersistanceException {
        return gestorUsuaris.getWinstreak(username);
    }

    public List<Double> getUserAvgAsMaker(String username) throws PersistanceException {
        return gestorUsuaris.getAvgAsMaker(username);
    }

    public List<Double> getUserAvgAsBreaker(String username) throws PersistanceException {
        return gestorUsuaris.getAvgAsBreaker(username);
    }

    public List<Integer> getUserNumGamesAsMaker(String username) throws PersistanceException {
        return gestorUsuaris.getNumGamesAsMaker(username);
    }

    public void setUserStats(String username, List<Integer> pr, List<Long> time, List<Integer> won, List<Integer> lost,
                             List<Integer> currentWs, List<Integer> ws, List<Double> avgMaker, List<Double> avgBreaker,
                             List<Integer> gamesMaker) throws PersistanceException {
        gestorUsuaris.setStats(username, pr, time, won, lost, currentWs, ws, avgMaker, avgBreaker, gamesMaker);
    }

    public Integer getNivellDificultatPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getDificultat(username);
    }

    public Integer getAlgorismePartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getAlgorisme(username);
    }

    public List<List<Integer>> getIntentsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getIntents(username);
    }

    public void setIntentsPartidaGuardada(String username, List<List<Integer>> intents) throws PersistanceException {
        gestorPartidesActuals.setIntents(username, intents);
    }

    public List<List<Integer>> getFeedbackPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getFeedbacks(username);
    }

    public void setFeedbacksPartidaGuardada(String username, List<List<Integer>> feedbacks) throws PersistanceException {
        gestorPartidesActuals.setFeedbacks(username, feedbacks);
    }

    public List<Integer> getSolucioPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getSolucio(username);
    }

    public Boolean existsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.existsPartidaActual(username);
    }

    public Boolean partidaGuardadaisBreaker(String username) throws PersistanceException {
        return gestorPartidesActuals.isBreaker(username);
    }


    public Long getTempsPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getTemps(username);
    }

    public void setTempsPartidaGuardada(String username, Long temps) throws PersistanceException {
        gestorPartidesActuals.setTemps(username, temps);
    }

    public Boolean getSolucioVistaPartidaGuardada(String username) throws PersistanceException {
        return gestorPartidesActuals.getSolucioVista(username);
    }

    public void setSolucioVistaPartidaGuardada(String username, Boolean solucioVista) throws PersistanceException {
        gestorPartidesActuals.setSolucioVista(username, solucioVista);
    }

    public void esborrarUsuari(String username) throws GeneralException {
        gestorUsuaris.esborrarUsuari(username);
    }

    public void esborrarPartida(String username) throws PersistanceException {
        gestorPartidesActuals.esborrarPartidaActual(username);
    }

    public void novaPartidaMaker(String username, List<Integer> solucio, Integer algorisme, List<List<Integer>> intents,
                                 List<List<Integer>> feedback) throws PersistanceException {
        gestorPartidesActuals.novaPartida(username, false, null, null, algorisme, solucio, intents, feedback);
    }
    public void novaPartidaBreaker(String username, Integer nivellDificultat, Long temps, List<Integer> solucio, List<List<Integer>> intents,
                                   List<List<Integer>> feedback) throws PersistanceException {
        gestorPartidesActuals.novaPartida(username, true, temps, nivellDificultat, null, solucio, intents, feedback);
    }
}
